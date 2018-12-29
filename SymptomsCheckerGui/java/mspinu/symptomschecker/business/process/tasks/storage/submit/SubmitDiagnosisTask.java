package mspinu.symptomschecker.business.process.tasks.storage.submit;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.ProgressBar;

import mspinu.symptomschecker.repository.dto.DiseaseDto;

import static mspinu.symptomschecker.commons.CommonUtils.initProgressBarDialog;

public class SubmitDiagnosisTask {
    private Thread backgroundThread;
    private ProgressBar progressBar;
    private Context context;
    private DiseaseDto diseaseDto;
    private SubmitDiagnosisRunnable submitDiagnosisRunnable;

    public SubmitDiagnosisTask(Context context){
        this.context = context;
        this.progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
        SubmitDiagnosisHandler submitDiagnosisHandler = new SubmitDiagnosisHandler(context);
        this.submitDiagnosisRunnable = new SubmitDiagnosisRunnable(context, submitDiagnosisHandler);
        this.backgroundThread = new Thread(submitDiagnosisRunnable);
        this.backgroundThread.setPriority(Thread.MIN_PRIORITY);
    }

    public void executeSubmitProcess(DiseaseDto diseaseDto) {
        this.diseaseDto = diseaseDto;
        execute();
    }

    private void preExecute(){
        AlertDialog progressBarDialog = initProgressBarDialog(progressBar);
        submitDiagnosisRunnable.setProgressBar(progressBarDialog);
        submitDiagnosisRunnable.setDisease(diseaseDto);
    }

    private void execute(){
        preExecute();
        backgroundThread.start();
    }

}
