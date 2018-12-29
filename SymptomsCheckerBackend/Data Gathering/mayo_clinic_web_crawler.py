# web scrapper/crawler that helped me gather medical data from mayo clinic


from string import ascii_uppercase
from bs4 import BeautifulSoup
import urllib
import re
import utils
import string

"""
Staring url from mayo clinic
"""
my_url = "https://www.mayoclinic.org/diseases-conditions/index?letter="

"""
Core link
"""
prefix = "https://www.mayoclinic.org/diseases-conditions/"  # each disease link has this core link
header_pattern = re.compile("h[0-9]+")  # regex to match a header
my_links = {}


def remove_unwanted_from(url):
    """

    :param url: Current url
    :return: Get url and disease name with no other characters
    """
    m_string = ""
    name = ""
    name_start = False
    url_start = False

    # get the individual link
    for i in range(1, len(url)):

        if url[i] == '>':
            break
        if url[i] == "/" and not url_start:
            url_start = True
            continue

        if url_start:
            m_string += url[i]

    # get the disease name
    for i in range(0, len(url)):
        if url[i] == '<':
            break

        if url[i] == '>':
            name_start = True
            continue

        if name_start:
            name += url[i]

    return str(m_string), str(name)


def filter_data(my_string):
    """

    :param my_string: Data
    :return: Filtered data of non ascii chars
    """
    printable = set(string.printable)
    my_string = my_string.encode('utf-8').lower().replace("\n", '', 1).replace("\n", ',')
    return filter(lambda i: i in printable, my_string)


def get_symptoms_from(url):
    """

    :param url: Current url
    :return: List of symptoms from given disease url
    """
    symptoms = []
    close_word = "sharetweet"

    if "symptoms-causes" not in url:
        url = url.replace("definition", "symptoms")

    page = urllib.urlopen(url).read()
    soup = BeautifulSoup(page, 'html.parser')
    items = soup.find_all(["ul", header_pattern, "p"])

    found_symptoms = False
    for item in items:

        if header_pattern.match(item.name) and found_symptoms:
            break

        if found_symptoms and item.name == "ul" and not item.has_attr("class") and item.text != close_word:
            symptoms.append(filter_data(item.text))

        if item.name == "p" and item.text[-1] == ":":
            found_symptoms = True
            continue

    if len(symptoms) == 0:  # different patterns found in the source html page and I made the scrapper by its template
        found_symptoms = False
        for item in items:

            if header_pattern.match(item.name) and item.text == "Overview" or item.text == "Symptoms":
                found_symptoms = True
                continue

            if found_symptoms and item.name == "p" and item.text != close_word:
                symptoms.append(filter_data(item.text))
                break

    return symptoms


def get_data():
    """

    :return: Get all the disease + symptoms (all steps above for all letters)
    """
    global my_url

    for letter in ascii_uppercase:  # change the index letter / diseases that start with current letter
        my_url += letter
        print letter
        for link in urllib.urlopen(my_url):
            for useful_link in re.findall("/diseases-conditions/.+", link):
                if "index" not in useful_link:
                    sub_link, name = remove_unwanted_from(useful_link)
                    source = prefix + sub_link
                    my_links[source] = name, get_symptoms_from(source)

        my_url = my_url[:-1]  # remove current letter, replaced by successor


get_data()
utils.write_to_csv_file('mayo_clinic.csv', my_links)
