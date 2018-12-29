package mspinu.symptomschecker.commons;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Suggestions extends ExpandableGroup<Suggestion> {
    public Suggestions(String title, List<Suggestion> items) {
        super(title, items);
    }
}
