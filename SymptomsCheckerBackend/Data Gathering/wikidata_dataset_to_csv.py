import utils
import queries

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
                if symptoms != "" and disease != "" and source != "":  # new data row
                    symptoms = list(set(symptoms))
                    symptoms = [item.encode('utf-8') for item in symptoms]  # convert to unicode
                    m_data[disease] = symptoms, source

                    disease = ""  # reset
                    symptoms = []
                    source = ""

                if isinstance(i, dict):  # fill new data
                    if "diseaseLabel" in i:
                        j = i.get("diseaseLabel")
                        disease = j.get("value").title().encode("utf-8")  # disease name as title
                    if "Symptoms" in i:
                        j = i.get("Symptoms")
                        symptoms = j.get("value").lower().split(", ")
                    if "disease" in i:
                        j = i.get("disease")
                        source = j.get("value").encode("utf-8")

        elif isinstance(value, dict):  # iterate through query result
            get_data(value)


get_data(utils.get_diseases("https://query.wikidata.org/sparql", queries.WIKIDATA_query))
utils.write_to_csv_file("wikidata.csv", m_data)
