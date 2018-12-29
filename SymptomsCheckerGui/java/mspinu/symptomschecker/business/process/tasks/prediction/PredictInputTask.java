package mspinu.symptomschecker.business.process.tasks.prediction;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.ProgressBar;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

import static mspinu.symptomschecker.commons.CommonUtils.initProgressBarDialog;
import static mspinu.symptomschecker.commons.ConnectionUtils.isConnectedToInternet;
import static mspinu.symptomschecker.commons.Constants.INTERNET_CONNECTION_ERROR_MESSAGE;
import static mspinu.symptomschecker.commons.Constants.NO_INPUT_WARN_MESSAGE;

/**
 * Created by Marius on 2/25/2018.
 */
public class PredictInputTask{
    private String userInput;
    private Thread backgroundThread;
    private PredictRunnable predictRunnable;
    private ProgressBar progressBar;
    private Context context;

    public PredictInputTask(String userInput, Context context) {
        this.userInput = userInput;
        this.context = context;
        PredictHandler predictHandler = new PredictHandler(context);
        this.progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
        this.predictRunnable = new PredictRunnable(userInput, context, predictHandler);
        this.backgroundThread = new Thread(predictRunnable);
        this.backgroundThread.setPriority(Thread.MIN_PRIORITY);
    }

    private void executePredictionProcess(String userInput) {

        if (!userInput.isEmpty()) {
            execute();

        } else {
            Toasty.warning(context, NO_INPUT_WARN_MESSAGE, Toast.LENGTH_LONG, true).show();
        }
    }
    private void preExecute(){
        AlertDialog progressBarDialog = initProgressBarDialog(progressBar);
        predictRunnable.setProgressBar(progressBarDialog);
    }

    private void execute(){
        preExecute();
        backgroundThread.start();
    }

    public void process() {
        if (isConnectedToInternet(context)) {
            executePredictionProcess(userInput);

        } else {
            Toasty.error(context, INTERNET_CONNECTION_ERROR_MESSAGE, Toast.LENGTH_LONG, true)
                    .show();
        }
    }

}
