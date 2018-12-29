package mspinu.symptomschecker.business.process.listeners;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;
import mspinu.symptomschecker.business.process.tasks.storage.submit.SubmitDiagnosisTask;
import mspinu.symptomschecker.repository.dto.DiseaseDto;

public class DiagnosisSubmitListener implements View.OnClickListener{
    private Context context;
    private DiseaseDto diseaseDto;
    private EditText diseaseNameField;

    public DiagnosisSubmitListener(Context context, DiseaseDto diseaseDto, EditText diseaseNameField){
        this.context = context;
        this.diseaseDto = diseaseDto;
        this.diseaseNameField = diseaseNameField;
    }

    @Override
    public void onClick(View view) {
        persistDisease(diseaseDto, diseaseNameField.getText().toString());
    }

    private void persistDisease(DiseaseDto diseaseDto, String diseaseName){
        if(diseaseName.isEmpty()){
            Toasty.error(context, "Please add disease name !", Toast.LENGTH_LONG, true).show();;
        }
        else{
            diseaseDto.setName(diseaseName);
            new SubmitDiagnosisTask(context).executeSubmitProcess(diseaseDto);
        }
    }
}
