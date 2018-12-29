package mspinu.symptomschecker.business.process.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import mspinu.symptomschecker.ui.activities.MedicalDiagnosisActivity;

/**
 * Created by Marius on 3/27/2018.
 */

public class SubmitDiagnosisClickListener implements View.OnClickListener{
    private Context context;

    public SubmitDiagnosisClickListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(context, MedicalDiagnosisActivity.class);
        context.startActivity(intent);
    }
}
