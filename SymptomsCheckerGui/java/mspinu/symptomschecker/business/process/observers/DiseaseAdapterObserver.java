package mspinu.symptomschecker.business.process.observers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Marius on 3/24/2018.
 */

public class DiseaseAdapterObserver extends RecyclerView.AdapterDataObserver {
    private LinearLayout placeHolderLayout;
    private RecyclerView.Adapter diseaseAdapter;

    public DiseaseAdapterObserver(LinearLayout placeHolderLayout, RecyclerView.Adapter diseaseAdapter){
        this.placeHolderLayout = placeHolderLayout;
        this.diseaseAdapter = diseaseAdapter;
    }

    @Override
    public void onChanged(){
        super.onChanged();
        isRecyclerViewEmpty();
    }

    @Override
    public void onItemRangeInserted(int positionStart, int itemCount){
        super.onItemRangeInserted(positionStart, itemCount);
        isRecyclerViewEmpty();
    }

    @Override
    public void onItemRangeRemoved(int positionStart, int itemCount){
        super.onItemRangeRemoved(positionStart, itemCount);
        isRecyclerViewEmpty();
    }


    private void isRecyclerViewEmpty(){
        placeHolderLayout.setVisibility(diseaseAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }
}
