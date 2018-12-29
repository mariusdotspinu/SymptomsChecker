package mspinu.symptomschecker.business.process.listeners;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import mspinu.symptomschecker.R;

public class SuggestionClickListener implements View.OnClickListener {

    private EditText userInput;

    public SuggestionClickListener(EditText userInput) {
        this.userInput = userInput;
    }

    @Override
    public void onClick(final View view) {
        String displayedText = view.getContext().getString(R.string.suggestion_clicked_text,
                userInput.getText().toString(), ((TextView) view).getText());

        userInput.setText(displayedText);
    }
}
