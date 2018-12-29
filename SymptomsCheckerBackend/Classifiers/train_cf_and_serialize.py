from sklearn.feature_extraction.text import CountVectorizer
from sklearn.naive_bayes import MultinomialNB
from sklearn.pipeline import Pipeline
from sklearn.linear_model import LogisticRegression
from sklearn.ensemble import RandomForestClassifier
import pickle

from utils import get_data, get_test_data


def train(data):
    """

    :param data: Train data
    :return: The 3 classifiers trained

    Train classifiers with data from DataSets created in Data Gathering module
    """

    bayes = Pipeline([  # wrapper
        ('vectorizer', CountVectorizer(stop_words='english')),  # extract features so that the algorithm can
                                                                #  learn from - word counts
        ('classifier', MultinomialNB())])

    logistic_regression = Pipeline([
        ('vectorizer', CountVectorizer(stop_words='english')),
        ('classifier', LogisticRegression())])

    r_forests = Pipeline([
        ('vectorizer', CountVectorizer(stop_words='english')),
        ('classifier', RandomForestClassifier(max_depth=1000, max_features=10))])

    bayes.fit(data['text'].values, data['label'].values)
    logistic_regression.fit(data['text'].values, data['label'].values)
    r_forests.fit(data['text'].values, data['label'].values)

    return bayes, logistic_regression, r_forests


def serialize(clf):
    """

    :param clf: Classifiers to be saved

    Saves the 3 trained classifiers in different text files for quick loadings in the future
    """

    pickle.dump(clf[0], open("bayes_naive.txt", "wb"))
    pickle.dump(clf[1], open("logistic_regression.txt", "wb"))
    pickle.dump(clf[2], open("random_forests.txt", "wb"))


serialize(train(get_data()))
