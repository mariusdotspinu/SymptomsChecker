package mspinu.symptomschecker.commons;

import android.os.Parcel;
import android.os.Parcelable;

public class Suggestion implements Parcelable {
    private String suggestionName;

    private Suggestion(Parcel in) {
    }

    public Suggestion(String suggestionName){
        this.suggestionName = suggestionName;
    }

    public String getSuggestionName() {
        return suggestionName;
    }

    public static final Creator<Suggestion> CREATOR = new Creator<Suggestion>() {
        @Override
        public Suggestion createFromParcel(Parcel in) {
            return new Suggestion(in);
        }

        @Override
        public Suggestion[] newArray(int size) {
            return new Suggestion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
