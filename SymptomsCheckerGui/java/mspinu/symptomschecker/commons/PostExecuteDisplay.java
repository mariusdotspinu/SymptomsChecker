package mspinu.symptomschecker.commons;

import android.content.Context;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mspinu.symptomschecker.ui.activities.MainActivity;

import static mspinu.symptomschecker.commons.Constants.DELIMITER;

/**
 * Created by Marius on 3/27/2018.
 */

public class PostExecuteDisplay {
    private Context context;

    public PostExecuteDisplay(Context context) {
        this.context = context;
    }

    public void showPredictResult(String predictResult) {
        Map<ArrayList<String>, String[]> prettyResultMap = getPrettyPredictionResult(predictResult);
        Map.Entry<ArrayList<String>, String[]> entry = prettyResultMap.entrySet().iterator().next();

        ((MainActivity) context).updateViewDataList(entry.getKey(), entry.getValue());
    }

    private Map<ArrayList<String>, String[]> getPredictionResultsMapFrom(String prediction[][],
                                                                         String[] suggestionList) {
        Map<ArrayList<String>, String[]> resultMap = new HashMap<>();
        ArrayList<String> results = new ArrayList<>();
        for (String[] currentPrediction : prediction) {
            results.add(buildCurrentResult(currentPrediction));
        }

        resultMap.put(results, suggestionList);
        return resultMap;
    }

    private String buildCurrentResult(String[] prediction) {
        StringBuilder current = new StringBuilder();

        current.append("Disease : ").append(prediction[0]).append(DELIMITER);
        current.append("Chance : ").append(getChanceWithTwoDecimals(prediction[1])).append("%")
                .append(DELIMITER);
        current.append("Symptoms : ").append(prediction[2])
                .append(DELIMITER);

        return current.toString();

    }

    private String getChanceWithTwoDecimals(String chance) {
        String[] defaultChanceNumber = chance.split("\\.");

        StringBuilder builder = new StringBuilder();
        builder.append(defaultChanceNumber[0]).append(".").append(defaultChanceNumber[1].charAt(0));

        if (defaultChanceNumber[1].length() > 1) {
            builder.append(defaultChanceNumber[1].charAt(1));
        }
        return builder.toString();
    }

    private Map<ArrayList<String>, String[]> getPrettyPredictionResult(String predictionResult) {
        Map<String[], String> resultMap = getParsedResultMap(predictionResult);
        Map.Entry<String[], String> entry = resultMap.entrySet().iterator().next();

        String[] suggestionList = entry.getKey();
        String predictResult = entry.getValue();

        return getPredictionResultsMapFrom(getPredictionJson(predictResult), suggestionList);
    }

    private Map<String[], String> getParsedResultMap(String result) {

        Map<String[], String> resultMap = new HashMap<>();
        String[] responses = result.split("], ", 2);

        String predictResult = getParsedPredictionString(responses[1]);
        String[] suggestions = getParsedSuggestionString(responses[0]).split(",");
        resultMap.put(trimStringList(suggestions), predictResult);

        return resultMap;
    }

    private String[] trimStringList(String[] list) {
        String[] newList = new String[list.length];
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i].trim();
        }
        return newList;
    }

    private String[][] getPredictionJson(String predictResult) {
        Gson gson = new Gson();
        PredictionJson predictionJson = gson.fromJson(predictResult, PredictionJson.class);

        return predictionJson.prediction;
    }

    private String getParsedPredictionString(String predictResponse) {
        return "{" + predictResponse;
    }

    private String getParsedSuggestionString(String suggestionResponse) {
        String replaceFirstPrefix = suggestionResponse.replace("{\"suggestions\": ", "");
        return replaceFirstPrefix.replace("[", "").replace("],", "").replace("\"", "");
    }

    class PredictionJson {
        String[][] prediction;
    }
}
