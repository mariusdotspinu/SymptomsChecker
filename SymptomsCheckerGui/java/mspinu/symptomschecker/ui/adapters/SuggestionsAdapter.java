package mspinu.symptomschecker.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import mspinu.symptomschecker.commons.Suggestion;
import mspinu.symptomschecker.ui.views.SuggestionViewHolder;
import mspinu.symptomschecker.ui.views.SuggestionsViewHolder;
import mspinu.symptomschecker.business.process.observers.SuggestionsAdapterObserver;
import mspinu.symptomschecker.R;

public class SuggestionsAdapter extends ExpandableRecyclerViewAdapter<SuggestionsViewHolder, SuggestionViewHolder> {
    private RecyclerView recyclerView;
    private EditText userInput;

    private SuggestionsAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    public SuggestionsAdapter(RecyclerView recyclerView, EditText userInput, List<? extends ExpandableGroup> groups){
        this(groups);
        this.recyclerView = recyclerView;
        this.userInput = userInput;
        SuggestionsAdapterObserver suggestionsAdapterObserver = new SuggestionsAdapterObserver(recyclerView, getItemCount());
        this.registerAdapterDataObserver(suggestionsAdapterObserver);
        suggestionsAdapterObserver.onChanged();
    }

    @Override
    public SuggestionsViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggestions_header_layout, parent, false);
        return new SuggestionsViewHolder(view, recyclerView);
    }

    @Override
    public SuggestionViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggestion_layout, parent, false);
        return new SuggestionViewHolder(view, userInput);
    }

    @Override
    public void onBindChildViewHolder(SuggestionViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Suggestion suggestion = (Suggestion) group.getItems().get(childIndex);
        holder.setSuggestion(suggestion.getSuggestionName());
    }

    @Override
    public void onBindGroupViewHolder(SuggestionsViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setSuggestionsTitle(group);
        holder.restoreArrow();
    }
}
