package mspinu.symptomschecker.business.process.tasks.storage.submit;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

import static mspinu.symptomschecker.commons.CommonUtils.dismissProgressBarDialog;

public class SubmitDiagnosisHandler extends Handler{
    private Context context;
    private AlertDialog progressBar;

    public SubmitDiagnosisHandler(Context context){
        this.context = context;
    }
    public void setProgressBar(AlertDialog progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void handleMessage(Message msg) {
        dismissProgressBarDialog(progressBar);
        ((Activity)context).finish();
        Toasty.success(context, "Thank you for your submission !", Toast.LENGTH_SHORT, true).show();
        super.handleMessage(msg);
    }
}
