package mspinu.symptomschecker.business.process.observers;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SuggestionsAdapterObserver extends RecyclerView.AdapterDataObserver {
    private RecyclerView suggestionRecyclerView;
    private int count;

    public SuggestionsAdapterObserver(RecyclerView suggestionRecyclerView, int count) {
        this.suggestionRecyclerView = suggestionRecyclerView;
        this.count = count;
    }

    @Override
    public void onChanged() {
        super.onChanged();
        isRecyclerViewEmpty();
    }

    private void isRecyclerViewEmpty() {
        suggestionRecyclerView.setVisibility(count != 0 ? View.VISIBLE : View.GONE);
    }
}
