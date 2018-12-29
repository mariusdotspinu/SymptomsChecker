package mspinu.symptomschecker.business.process.tasks.storage.fetch;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import mspinu.symptomschecker.repository.dto.DiseaseDto;
import mspinu.symptomschecker.ui.adapters.SubmissionsAdapter;

import static mspinu.symptomschecker.commons.CommonUtils.dismissProgressBarDialog;
import static mspinu.symptomschecker.commons.Constants.SUBMISSION_KEY;
import static mspinu.symptomschecker.commons.Constants.SUBMISSION_MESSAGE;

public class SubmissionHandler extends Handler{
    private AlertDialog progressBar;
    private SubmissionsAdapter adapter;
    private RecyclerView submissionsList;
    private TextView placeholder;

    public SubmissionHandler(SubmissionsAdapter adapter, RecyclerView submissionsList, TextView placeHolder){
        this.submissionsList = submissionsList;
        this.adapter = adapter;
        this.placeholder = placeHolder;
    }
    public void setProgressBar(AlertDialog progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.what == SUBMISSION_MESSAGE) {
            dismissProgressBarDialog(progressBar);
            fillSubmissionList((List<DiseaseDto>) msg.getData().get(SUBMISSION_KEY));
        }
        super.handleMessage(msg);
    }

    private void fillSubmissionList(List<DiseaseDto> diseaseDtoList){
        if (!diseaseDtoList.isEmpty()){
            adapter.setSubmissions(diseaseDtoList);
            submissionsList.setVisibility(View.VISIBLE);
            placeholder.setVisibility(View.GONE);
            submissionsList.setAdapter(adapter);
        }
    }
}
