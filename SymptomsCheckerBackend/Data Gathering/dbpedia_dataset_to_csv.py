import sys
import utils
import queries

reload(sys)
sys.setdefaultencoding('utf-8')  # all strings encoded from the beginning with utf-8

m_data = {}


def get_data(m_dict):
    """

    :param m_dict: Dictionary with queried data
    :return: Data formatted for CSV format - > m_data
    """
    global m_data

    disease = ""
    symptoms = []
    source = ""

    for key, value in m_dict.iteritems():
        if isinstance(value, list):  # found new data
            for i in value:
                if len(symptoms) != 0 and disease != "" and source != "":  # new data row

                    symptoms = [item.decode("utf-8").encode("ascii", "ignore") for item in symptoms]
                    m_data[disease] = symptoms, source

                    disease = ""  # reset
                    symptoms = []
                    source = ""

                if isinstance(i, dict):  # fill new data
                    if "Disease" in i:
                        j = i.get("Disease")
                        disease = j.get("value").title().encode("utf-8")  # disease name as title
                    if "Symptoms" in i:
                        j = i.get("Symptoms")
                        symptoms = j.get("value").lower().split(", ")
                    if "Source" in i:
                        j = i.get("Source")
                        source = j.get("value").encode("utf-8")

        elif isinstance(value, dict):  # iterate through query result
            get_data(value)


get_data(utils.get_diseases("http://dbpedia.org/sparql", queries.DBPEDIA_query))
utils.write_to_csv_file("dbpedia.csv", m_data)
