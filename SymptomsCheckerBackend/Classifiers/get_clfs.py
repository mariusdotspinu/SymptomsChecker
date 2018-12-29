import os
import pickle

bayes_file = os.path.join(os.path.dirname(__file__), 'bayes_naive.txt')
logistic_file = os.path.join(os.path.dirname(__file__), 'logistic_regression.txt')
random_forests_files = os.path.join(os.path.dirname(__file__), 'random_forests.txt')


def get_bayes():
    """
    :return: Bayes classifier pickle stream

    Loads the bayes classifier in memory from requested file
    """

    return pickle.load(open(bayes_file, "rb"))


def get_regression():
    """
    :return: Logistic regression classifier pickle stream

    Loads the logistic regression classifier in memory from requested file
    """

    return pickle.load(open(logistic_file, "rb"))


def get_random_forest():
    """
    :return: Random forests classifier pickle stream

    Loads the random forests classifier in memory from requested file
    """

    return pickle.load(open(random_forests_files, "rb"))

# print "Train Accuracy : ", accuracy_score(data['label'].values, pipeline.predict(data['text'].values))
# print "Test Accuracy  : ", accuracy_score(test_data['label'].values, pipe_test.predict(test_data['text'].values))
