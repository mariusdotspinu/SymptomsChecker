package mspinu.symptomschecker.business.process.listeners;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public class DiagnosisCancelListener implements View.OnClickListener{
    private Context context;
    public DiagnosisCancelListener(Context context){
        this.context = context;
    }
    @Override
    public void onClick(View view) {
        ((Activity)context).finish();
    }
}