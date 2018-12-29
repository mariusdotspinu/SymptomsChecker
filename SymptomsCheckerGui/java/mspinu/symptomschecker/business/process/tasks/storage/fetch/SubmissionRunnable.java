package mspinu.symptomschecker.business.process.tasks.storage.fetch;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import mspinu.symptomschecker.business.process.facade.DiseaseFacadeImpl;
import mspinu.symptomschecker.repository.dto.DiseaseDto;
import mspinu.symptomschecker.ui.activities.SubmissionsActivity;

import static mspinu.symptomschecker.commons.CommonUtils.initProgressBarDialog;
import static mspinu.symptomschecker.commons.Constants.SUBMISSION_KEY;
import static mspinu.symptomschecker.commons.Constants.SUBMISSION_MESSAGE;

public class SubmissionRunnable implements Runnable{

    private Context context;
    private AlertDialog progressBarDialog;
    private SubmissionHandler handler;

    public SubmissionRunnable(Context context, SubmissionHandler handler){
        this.handler = handler;
        this.context = context;
        progressBarDialog = initProgressBarDialog(new ProgressBar(context, null, android.R.attr.progressBarStyleLarge));
    }
    @Override
    public void run() {
        ((SubmissionsActivity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBarDialog.show();
            }
        });

        List<DiseaseDto> diseaseDtoList = getSubmissions(this.context);
        handler.setProgressBar(progressBarDialog);
        handler.sendMessage(getSubmissionMessage(diseaseDtoList));
    }

    private List<DiseaseDto> getSubmissions(Context context){
        DiseaseFacadeImpl facade = new DiseaseFacadeImpl(context);
        return facade.getAllDiseasesFromDb();
    }

    private Message getSubmissionMessage(List<DiseaseDto> diseaseDtoList){
        Message message = new Message();
        message.setData(getSubmissionBundle(diseaseDtoList));
        message.what = SUBMISSION_MESSAGE;
        return message;
    }

    private Bundle getSubmissionBundle(List<DiseaseDto> diseaseDtoList){
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(SUBMISSION_KEY, new ArrayList<Parcelable>(diseaseDtoList));
        return bundle;
    }
}
