package mspinu.symptomschecker.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import mspinu.symptomschecker.R;
import mspinu.symptomschecker.business.process.listeners.DiagnosisCancelListener;
import mspinu.symptomschecker.business.process.listeners.DiagnosisSubmitListener;
import mspinu.symptomschecker.repository.dto.DiseaseDto;
import mspinu.symptomschecker.ui.adapters.DiagnosisAdapter;
import mspinu.symptomschecker.ui.utils.UiUtils;

import static mspinu.symptomschecker.commons.Constants.DIAGNOSIS_DISEASE;
import static mspinu.symptomschecker.commons.Constants.DIAGNOSIS_SYMPTOMS;
import static mspinu.symptomschecker.commons.Constants.EMPTY;

public class MedicalDiagnosisActivity extends AppCompatActivity {

    private EditText symptomsEdt;
    private RecyclerView symptomsList;
    private ArrayList<String> symptomsStringList;
    private DiagnosisAdapter adapter;
    private DiseaseDto diseaseDto;
    private EditText diseaseEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_diagnosis);
        setTitle("Medical Diagnosis Submission");

        Button submitBtn = findViewById(R.id.submitBtn);
        Button cancelBtn = findViewById(R.id.cancelBtn);
        Button addBtn = findViewById(R.id.addBtn);

        diseaseEditText = findViewById(R.id.diseaseEdt);
        symptomsEdt = findViewById(R.id.symptomsEdt);

        symptomsList = findViewById(R.id.symptomsList);
        symptomsList.setLayoutManager(new LinearLayoutManager(this));
        symptomsList.addItemDecoration(UiUtils.getItemDecorator(this));

        symptomsStringList = new ArrayList<>();
        adapter = new DiagnosisAdapter();
        diseaseDto = new DiseaseDto();

        submitBtn.setOnClickListener(new DiagnosisSubmitListener(this, diseaseDto,
                diseaseEditText));
        cancelBtn.setOnClickListener(new DiagnosisCancelListener(this));

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSymptom();
                symptomsEdt.clearComposingText();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(DIAGNOSIS_SYMPTOMS, (ArrayList<String>) adapter.getDiagnosisSymptoms());
        outState.putString(DIAGNOSIS_DISEASE, diseaseEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            diseaseEditText.setText(savedInstanceState.getString(DIAGNOSIS_DISEASE));
            adapter = new DiagnosisAdapter();
            adapter.setDiagnosisSymptoms(savedInstanceState.getStringArrayList(DIAGNOSIS_SYMPTOMS));
            changeListVisibility();
            symptomsList.setAdapter(adapter);
        }
    }

    private void addSymptom() {
        String symptom = symptomsEdt.getText().toString();

        if (!symptom.isEmpty()) {
            symptomsStringList.add(symptom);
            changeListVisibility();
            adapter.setDiagnosisSymptoms(symptomsStringList);
            symptomsList.setAdapter(adapter);
            diseaseDto.getSymptoms().add(symptom);
        } else {
            Toasty.warning(this, "Please add a symptom", Toast.LENGTH_SHORT, true).show();
        }

        symptomsEdt.setText(EMPTY);

    }

    private void changeListVisibility() {
        if (symptomsList.getVisibility() == View.INVISIBLE) {
            symptomsList.setVisibility(View.VISIBLE);
        } else if (symptomsStringList.isEmpty()) {
            symptomsList.setVisibility(View.INVISIBLE);
        }
    }
}