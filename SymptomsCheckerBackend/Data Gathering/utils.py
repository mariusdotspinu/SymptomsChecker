import SPARQLWrapper
import csv


def get_diseases(environment, query):
    """

    :param environment: Database environment (DB-PEDIA/WIKI-DATA)
    :param query: Query for disease extraction
    :return:
    """
    spar_ql = SPARQLWrapper.SPARQLWrapper(environment)
    spar_ql.setReturnFormat(SPARQLWrapper.JSON)

    spar_ql.setQuery(query)

    return spar_ql.query().convert()


def write_to_csv_file(file_name, m_data):
    """

    :param file_name: Name of csv file to be saved in
    :param m_data: Data to be saved
    :return:
    """
    with open(file_name, "wb") as f:
        labels = ['Disease', 'Symptoms', 'Source']
        writer = csv.writer(f, delimiter=';')
        writer.writerow(labels)

        if file_name == 'mayo_clinic.csv':
            for link, result in m_data.iteritems():
                writer.writerow([result[0], result[1], link])
        else:
            for link, result in m_data.iteritems():
                writer.writerow([link, result[0], result[1]])
