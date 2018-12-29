from Classifiers import utils
from rake_nltk import Rake
import re

data = utils.get_data()


def remove_duplicate_disease_labels(m_list):
    """

    :param m_list: List of disease labels
    :return: Unique list in case of duplicates (it is possible for a disease to be present in more than one DataSet)
    """
    unique_list = []
    labels = []

    for item in m_list:
        if item[0] not in labels:
            labels.append(item[0])
            unique_list.append(item)

    return unique_list


def get_top_5_keywords_from_detailed_symptoms(disease_symptoms):
    """

    :param disease_symptoms: Symptoms
    :return: Returns keywords for suggestions based on that symptoms, to help the user
    """
    rake_english = Rake()
    rake_english.extract_keywords_from_text(disease_symptoms)

    top_suggestions = sorted(rake_english.get_ranked_phrases_with_scores(), key=lambda x: x[0], reverse=True)[0:5]
    top_suggestions = [re.sub('[^0-9a-zA-Z]+', ' ', suggestion[1]) for suggestion in top_suggestions]
    return top_suggestions


def get_top_results(user_input, bayes_clf, logistic_regression_clf, random_forest_clf):
    """

    :param user_input: User's symptoms
    :param bayes_clf: Bayes clf
    :param logistic_regression_clf: Logistic Regression clf
    :param random_forest_clf: Random Forest clf
    :return: Diseases with the symptoms that matched the user's input
    """
    b_prediction = bayes_clf.predict_proba([user_input])[0]
    l_prediction = logistic_regression_clf.predict_proba([user_input])[0]
    rf_prediction = random_forest_clf.predict_proba([user_input])[0]

    top_results_b = sorted(zip(bayes_clf.classes_, b_prediction), key=lambda x: x[1], reverse=True)[0:5]
    top_results_l = sorted(zip(logistic_regression_clf.classes_, l_prediction), key=lambda x: x[1], reverse=True)[0:5]
    top_results_rf = sorted(zip(random_forest_clf.classes_, rf_prediction), key=lambda x: x[1], reverse=True)[0:5]

    top_results_b.extend(top_results_l)
    top_results_b.extend(top_results_rf)

    results = remove_duplicate_disease_labels(sorted(top_results_b, key=lambda x: x[1], reverse=True))
    final_results = []
    suggestions = []

    for result in results:
        if len(result) > 0:
            sliced_data = data.loc[data['label'] == result[0]]
            if len(sliced_data) > 0 and result[1] > 0:
                symptoms = sliced_data.values[0][1]
                final_results.append((result[0], result[1] * 100, symptoms))
                suggestions.extend(get_top_5_keywords_from_detailed_symptoms(symptoms))

    suggestions = list(set(suggestions))
    return final_results, suggestions
