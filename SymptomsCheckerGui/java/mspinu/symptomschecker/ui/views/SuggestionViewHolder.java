package mspinu.symptomschecker.ui.views;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import mspinu.symptomschecker.business.process.listeners.SuggestionClickListener;
import mspinu.symptomschecker.R;

public class SuggestionViewHolder extends ChildViewHolder {
    private TextView suggestion;

    public SuggestionViewHolder(final View itemView, EditText userInput) {
        super(itemView);
        suggestion = itemView.findViewById(R.id.suggestion);
        suggestion.setOnClickListener(new SuggestionClickListener(userInput));

    }

    public void setSuggestion(String suggestion) {
        this.suggestion.setText(suggestion);
    }

}
