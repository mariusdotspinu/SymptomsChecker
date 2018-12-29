import sys
import os.path

sys.path.append(os.path.join(os.path.dirname(__file__), '..'))
from flask import Flask
from flask_restful import Resource, Api
from Classifiers import get_clfs
from process_user_input import get_top_results

app = Flask(__name__)
api = Api(app)


class Predict(Resource):

    def get(self, user_input):
        result, suggestions = get_top_results(user_input, bayes, logistic_regression, random_forests)
        return {'prediction': result, 'suggestions': suggestions}


api.add_resource(Predict, '/predict/<string:user_input>')

if __name__ == '__main__':
    bayes = get_clfs.get_bayes()
    logistic_regression = get_clfs.get_regression()
    random_forests = get_clfs.get_random_forest()
    app.run()
