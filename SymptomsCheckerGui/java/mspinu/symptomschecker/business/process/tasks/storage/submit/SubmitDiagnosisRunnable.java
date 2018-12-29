package mspinu.symptomschecker.business.process.tasks.storage.submit;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import mspinu.symptomschecker.ui.activities.MedicalDiagnosisActivity;
import mspinu.symptomschecker.business.process.facade.DiseaseFacadeImpl;
import mspinu.symptomschecker.repository.dto.DiseaseDto;

import static mspinu.symptomschecker.commons.Constants.FINISH;

public class SubmitDiagnosisRunnable implements Runnable{
    private Context context;
    private AlertDialog progressBar;
    private SubmitDiagnosisHandler handler;
    private DiseaseDto diseaseDto;

    public SubmitDiagnosisRunnable(Context context, SubmitDiagnosisHandler handler){
        this.context = context;
        this.handler = handler;
    }

    public void setProgressBar(AlertDialog progressBar) {
        this.progressBar = progressBar;
    }

    public void setDisease(DiseaseDto diseaseDto){
        this.diseaseDto = diseaseDto;
    }

    @Override
    public void run() {
        ((MedicalDiagnosisActivity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.show();
            }
        });

        new DiseaseFacadeImpl(context).addDisease(diseaseDto);
        handler.setProgressBar(progressBar);
        handler.sendEmptyMessage(FINISH);
    }
}
