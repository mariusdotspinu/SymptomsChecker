package mspinu.symptomschecker.business.process.tasks.prediction;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.net.HttpURLConnection;

import es.dmoral.toasty.Toasty;
import mspinu.symptomschecker.commons.PostExecuteDisplay;

import static mspinu.symptomschecker.commons.CommonUtils.dismissProgressBarDialog;
import static mspinu.symptomschecker.commons.CommonUtils.showErrorDialog;
import static mspinu.symptomschecker.commons.Constants.HOST_ADDRESS_CONNECTION_ERROR_MESSAGE;
import static mspinu.symptomschecker.commons.Constants.SERVER_CONNECTION_ERROR_MESSAGE;

public class PredictHandler extends Handler{
    private AlertDialog progressBar;
    private HttpURLConnection httpURLConnection;
    private Context context;
    private String predictionResult;

    public PredictHandler(Context context){
        this.context = context;
    }

    public void setProgressBar(AlertDialog progressBar) {
        this.progressBar = progressBar;
    }

    public void setHttpURLConnection(HttpURLConnection httpURLConnection) {
        this.httpURLConnection = httpURLConnection;
    }

    public void setPredictionResult(String predictionResult) {
        this.predictionResult = predictionResult;
    }

    @Override
    public void handleMessage(Message msg) {
        postExecute(predictionResult);
        super.handleMessage(msg);
    }

    private void postExecute(String predictionResult){
            dismissProgressBarDialog(progressBar);

            if (httpURLConnection == null) {
                Toasty.error(context, HOST_ADDRESS_CONNECTION_ERROR_MESSAGE, Toast.LENGTH_LONG).show();
                return;
            }

            if (predictionResult != null && !predictionResult.isEmpty()) {
                new PostExecuteDisplay(context).showPredictResult(predictionResult);
            } else {
                showErrorDialog(SERVER_CONNECTION_ERROR_MESSAGE, context, false);
            }
        }
}
