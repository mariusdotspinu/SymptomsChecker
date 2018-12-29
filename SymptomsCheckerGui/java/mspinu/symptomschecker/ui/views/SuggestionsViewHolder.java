package mspinu.symptomschecker.ui.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import mspinu.symptomschecker.R;

import static android.view.animation.Animation.RELATIVE_TO_SELF;
import static mspinu.symptomschecker.commons.Constants.EXPAND;

public class SuggestionsViewHolder extends GroupViewHolder {

    private RecyclerView recyclerView;
    private TextView suggestionsTitle;
    private ImageView arrow;
    private boolean isExpandedRestored = false;

    public SuggestionsViewHolder(View itemView, RecyclerView recyclerView) {
        super(itemView);
        suggestionsTitle = itemView.findViewById(R.id.suggestions_header);
        arrow = itemView.findViewById(R.id.arrow);
        this.recyclerView = recyclerView;
    }

    public void setSuggestionsTitle(ExpandableGroup group) {
        suggestionsTitle.setText(group.getTitle());
    }

    public void restoreArrow(){
        if(isExpandedRestored){
            RotateAnimation rotate;
            rotate = new RotateAnimation(360, 180 , RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(0);
            rotate.setFillAfter(true);
            arrow.setAnimation(rotate);
        }
    }

    @Override
    public void expand() {
        expandAnimation();
        isExpandedRestored = true;
        recyclerView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        recyclerView.requestLayout();
    }

    @Override
    public void collapse() {
        collapseAnimation();
        isExpandedRestored = false;
        recyclerView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        recyclerView.requestLayout();
    }

    private void expandAnimation(){
        arrow.setAnimation(getRotation(EXPAND));
    }

    private void collapseAnimation(){
        arrow.setAnimation(getRotation(!EXPAND));
    }

    private RotateAnimation getRotation(boolean expand){
        RotateAnimation rotate;
        if(expand){
            rotate = new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(300);
            rotate.setFillAfter(true);
        }
        else{
            rotate = new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(300);
            rotate.setFillAfter(true);
        }
        return rotate;
    }
}
