import re
import numpy as np
from pandas import DataFrame

"""
Paths of DataSets for training
"""
sources_train = ['D:\symptomschecker backend\Data Gathering\wikidata.csv',
                 'D:\symptomschecker backend\Data Gathering\mayo_clinic.csv']

"""
Paths of DataSets for testing
"""
sources_test = ['D:\symptomschecker backend\Data Gathering\dbpedia.csv',
                'D:\symptomschecker backend\Data Gathering\wikidata.csv']


def pretty_format_feature(feature):
    """

    :param feature: symptoms data
    :return: Symptoms data without unwanted characters

    Remove unwanted characters from symptoms column from csv file
    """

    return re.sub('["\[\]]', '', feature)  # remove "[" and "] from symptoms list


def build_data_frame(file_path):
    """
    :param file_path: DataSet path
    :return: DataFrame of data based on the respective DataSet


    Builds the DataFrame from the given DataSet
    """
    data_set = []
    index = []

    with open(file_path, 'rb') as _file:
        for row in _file.readlines():
            data = row.split(';')
            if data[0] == "Disease":  # don't take the header
                continue

            label = data[0]
            features = data[1]
            data_item = {"label": label, "text": pretty_format_feature(features)}
            data_set.append(data_item)

            # add index label
            index.append(data[2])

    data_frame = DataFrame(data_set, index=index)
    return data_frame


def get_data():
    """

    :return: Built data

    Builds one DataSet for all the train sources (DataFrames)
    """
    data = DataFrame({"label": [], "text": []})

    for path in sources_train:
        data = data.append(build_data_frame(path), ignore_index=True)

    data_set = data.reindex(np.random.permutation(data.index))  # shuffle the set of data

    return data_set


def get_test_data():
    """

    :return: Built data

    Builds one DataSet for all the test sources (DataFrames)
    """
    data = DataFrame({"label": [], "text": []})

    for path in sources_test:
        data = data.append(build_data_frame(path), ignore_index=True)

    data_set = data.reindex(np.random.permutation(data.index))  # shuffle the set of data

    return data_set
