package mspinu.symptomschecker.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mspinu.symptomschecker.R;
import mspinu.symptomschecker.business.process.listeners.PredictClickListener;
import mspinu.symptomschecker.business.process.listeners.SubmitDiagnosisClickListener;
import mspinu.symptomschecker.commons.Suggestion;
import mspinu.symptomschecker.commons.Suggestions;
import mspinu.symptomschecker.ui.adapters.DiseaseAdapter;
import mspinu.symptomschecker.ui.adapters.SuggestionsAdapter;
import mspinu.symptomschecker.ui.utils.UiUtils;

import static mspinu.symptomschecker.commons.Constants.DISEASES_DATA;
import static mspinu.symptomschecker.commons.Constants.SAVED_POSITION;
import static mspinu.symptomschecker.commons.Constants.SUGGESTIONS_KEY;
import static mspinu.symptomschecker.commons.Constants.SUGGESTIONS_TITLE;

public class MainActivity extends AppCompatActivity {
    Button predict;
    FloatingActionButton submitDiagnosisFab;
    EditText input;
    RecyclerView diseaseRecyclerView, suggestionsRecyclerView;
    LinearLayoutManager diseaseLinearLayoutManager, suggestionsLinearLayoutManager;
    LinearLayout placeHolderLayout;
    DiseaseAdapter diseaseAdapter;
    SuggestionsAdapter suggestionsAdapter;
    List<Suggestions> suggestionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        predict = findViewById(R.id.predictBtn);
        submitDiagnosisFab = findViewById(R.id.submitDiagnosis);
        input = findViewById(R.id.userInput);

        diseaseRecyclerView = findViewById(R.id.disease_recycler_view);
        diseaseRecyclerView.setHasFixedSize(true);

        suggestionsRecyclerView = findViewById(R.id.suggestions_recycler_view);
        suggestionsRecyclerView.setHasFixedSize(true);

        diseaseLinearLayoutManager = new LinearLayoutManager(this);
        suggestionsLinearLayoutManager = new LinearLayoutManager(this);

        suggestionsRecyclerView.addItemDecoration(UiUtils.getItemDecorator(this));

        placeHolderLayout = findViewById(R.id.placeholder);
        diseaseRecyclerView.setLayoutManager(diseaseLinearLayoutManager);
        suggestionsRecyclerView.setLayoutManager(suggestionsLinearLayoutManager);

        predict.setOnClickListener(new PredictClickListener(input, this));
        diseaseAdapter = new DiseaseAdapter(placeHolderLayout);

        suggestionsList = new ArrayList<>();
        submitDiagnosisFab.setOnClickListener(new SubmitDiagnosisClickListener(this));

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public void updateViewDataList(ArrayList<String> prettyResult, String[] suggestions) {
        diseaseAdapter.setDiseasesInformation(prettyResult);
        refresh(suggestionsRecyclerView);

        suggestionsList = getSuggestions(suggestions);
        suggestionsAdapter = new SuggestionsAdapter(suggestionsRecyclerView, input, suggestionsList);

        diseaseRecyclerView.setAdapter(diseaseAdapter);
        suggestionsRecyclerView.setAdapter(suggestionsAdapter);

    }

    /**
     * @param recyclerView
     * Collapses suggestion recycler view, in order for the disease data to show,
     * since the state will be reloaded
     */
    private void refresh(RecyclerView recyclerView){
        recyclerView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        recyclerView.requestLayout();
    }

    private List<Suggestions> getSuggestions(String[] suggestions) {
        List<Suggestion> suggestionList = new ArrayList<>();
        Suggestions suggestionsHeader = new Suggestions(SUGGESTIONS_TITLE, suggestionList);

        for (String stringSuggestion : suggestions) {
            suggestionList.add(new Suggestion(stringSuggestion));
        }

        return Collections.singletonList(suggestionsHeader);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(DISEASES_DATA, diseaseAdapter.getDiseasesInformation());
        outState.putParcelableArrayList(SUGGESTIONS_KEY, new ArrayList<Parcelable>(suggestionsList));
        outState.putInt(SAVED_POSITION, diseaseLinearLayoutManager.findFirstCompletelyVisibleItemPosition());
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            suggestionsList = savedInstanceState.getParcelableArrayList(SUGGESTIONS_KEY);
            suggestionsAdapter = new SuggestionsAdapter(suggestionsRecyclerView, input, suggestionsList);
            diseaseAdapter.setDiseasesInformation(savedInstanceState.getStringArrayList(DISEASES_DATA));
            diseaseLinearLayoutManager.scrollToPosition(savedInstanceState.getInt(SAVED_POSITION));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        diseaseRecyclerView.setAdapter(diseaseAdapter);
        suggestionsRecyclerView.setAdapter(suggestionsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.submission:
                startActivity(new Intent(this, SubmissionsActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
