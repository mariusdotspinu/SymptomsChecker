package mspinu.symptomschecker.commons;

/**
 * Created by Marius on 2/25/2018.
 */

public class Constants {
    public static final String IP_ADDRESS_IDENTIFIER = "https://schecker.serveo.net";
    public static final String INTERNET_CONNECTION_ERROR_MESSAGE = "Please connect to the internet !";
    public static final String HOST_ADDRESS_CONNECTION_ERROR_MESSAGE = "Could not resolve host address !";
    public static final String SERVER_CONNECTION_ERROR_MESSAGE = "Could not connect to server !";
    public static final String ERROR_TITLE = "Error";
    public static final String POSITIVE_BUTTON_TEXT = "Ok";
    public static final String DELIMITER = "#$";
    public static final String PREDICT_ENDPOINT = "/predict/";
    public static final String PROGRESS_BAR_TITLE = "Processing...";
    public static final String EMPTY = "";
    public static final String NO_INPUT_WARN_MESSAGE = "Please enter symptoms !";
    public static final String WELCOME_TEXT = "Symptoms Checker";
    public static final String DISEASES_DATA = "data";
    public static final String SAVED_POSITION = "lastScrollPosition";
    public static final String SUGGESTIONS_TITLE = "SUGGESTIONS";
    public static final String SUGGESTIONS_KEY = "suggestions_values";
    public static final String SUBMISSION_KEY = "disease_list";
    public static final String DIAGNOSIS_SYMPTOMS = "symptoms_list";
    public static final String DIAGNOSIS_DISEASE = "disease";

    public static final int FINISH = 1;
    public static final int SUBMISSION_MESSAGE = 1;

    public static final short CONNECTION_TIMEOUT = 1000;
    public static final short DELAY_TIME = 3000;

    public static final char MINIMUM_CHANCE = '3';

    public static final boolean EXPAND = true;
}
