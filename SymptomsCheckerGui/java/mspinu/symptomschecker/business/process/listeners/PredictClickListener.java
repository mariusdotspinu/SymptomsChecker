package mspinu.symptomschecker.business.process.listeners;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import mspinu.symptomschecker.business.process.tasks.prediction.PredictInputTask;

/**
 * Created by Marius on 3/27/2018.
 */

public class PredictClickListener implements View.OnClickListener {
    private EditText inputEditText;
    private Context context;

    public PredictClickListener(EditText inputEditText, Context context) {
        this.inputEditText = inputEditText;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        new PredictInputTask(inputEditText.getText().toString(), context).process();
    }
}
