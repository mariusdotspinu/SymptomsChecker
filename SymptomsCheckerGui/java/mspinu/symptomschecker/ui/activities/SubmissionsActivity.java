package mspinu.symptomschecker.ui.activities;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import mspinu.symptomschecker.R;
import mspinu.symptomschecker.business.process.tasks.storage.fetch.SubmissionHandler;
import mspinu.symptomschecker.business.process.tasks.storage.fetch.SubmissionRunnable;
import mspinu.symptomschecker.repository.dto.DiseaseDto;
import mspinu.symptomschecker.ui.adapters.SubmissionsAdapter;

import static mspinu.symptomschecker.commons.Constants.SUBMISSION_KEY;

public class SubmissionsActivity extends AppCompatActivity {
    RecyclerView submissionList;
    SubmissionsAdapter adapter;
    SubmissionHandler handler;
    TextView placeholder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("My Submissions");
        initBackButton();
        setContentView(R.layout.activity_submission_layout);
        submissionList = findViewById(R.id.submissionList);
        submissionList.setLayoutManager(new LinearLayoutManager(this));
        placeholder = findViewById(R.id.submission_placeholder);
        adapter = new SubmissionsAdapter();
        handler = new SubmissionHandler(adapter, submissionList, placeholder);

        SubmissionRunnable runnable = new SubmissionRunnable(this, handler);
        new Thread(runnable).start();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(SUBMISSION_KEY, new ArrayList<Parcelable>(adapter.getSubmissions()));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        adapter = new SubmissionsAdapter();
        adapter.setSubmissions(savedInstanceState.<DiseaseDto>getParcelableArrayList(SUBMISSION_KEY));
        submissionList.setAdapter(adapter);
    }

    private void initBackButton(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

