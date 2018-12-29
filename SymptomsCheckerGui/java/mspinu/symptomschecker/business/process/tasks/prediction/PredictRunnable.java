package mspinu.symptomschecker.business.process.tasks.prediction;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import java.io.IOException;
import java.net.HttpURLConnection;

import mspinu.symptomschecker.ui.activities.MainActivity;

import static mspinu.symptomschecker.commons.CommonUtils.getResultFromStream;
import static mspinu.symptomschecker.commons.ConnectionUtils.getConnection;
import static mspinu.symptomschecker.commons.Constants.EMPTY;
import static mspinu.symptomschecker.commons.Constants.FINISH;
import static mspinu.symptomschecker.commons.Constants.IP_ADDRESS_IDENTIFIER;

public class PredictRunnable implements Runnable{
    private HttpURLConnection urlConnection;
    private Context context;
    private AlertDialog progressBar;
    private PredictHandler handler;

    public void setProgressBar(AlertDialog progressBar) {
        this.progressBar = progressBar;
    }
    private String userInput;
    private String predictionResult;

    public PredictRunnable(String userInput, Context context, PredictHandler handler){
        this.userInput = userInput;
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void run() {
        ((MainActivity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.show();
            }
        });

        try {
            urlConnection = getConnection(IP_ADDRESS_IDENTIFIER, userInput);
            if (urlConnection != null) {
                predictionResult = getResultFromStream(urlConnection.getInputStream());
            }
            else{
                predictionResult = EMPTY;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        handler.setPredictionResult(predictionResult);
        handler.setProgressBar(progressBar);
        handler.setHttpURLConnection(urlConnection);
        handler.sendEmptyMessage(FINISH);
    }
}
