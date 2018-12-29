package mspinu.symptomschecker.ui.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mspinu.symptomschecker.business.process.observers.DiseaseAdapterObserver;
import mspinu.symptomschecker.R;

import static mspinu.symptomschecker.commons.Constants.MINIMUM_CHANCE;

/**
 * Created by Marius on 2/28/2018.
 */

public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.ViewHolder> {
        private ArrayList<String> diseasesInformation;
    private DiseaseAdapterObserver diseaseAdapterObserver;

    public DiseaseAdapter(LinearLayout placeHolderLayout) {
        diseasesInformation = new ArrayList<>();
        diseaseAdapterObserver = new DiseaseAdapterObserver(placeHolderLayout, this);
        this.registerAdapterDataObserver(diseaseAdapterObserver);
    }

    public void setDiseasesInformation(ArrayList<String> diseasesInformation) {
        this.diseasesInformation = diseasesInformation;
        diseaseAdapterObserver.onChanged();
    }

    public ArrayList<String> getDiseasesInformation() {
        return diseasesInformation;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.disease_layout, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String[] values = diseasesInformation.get(position).split("#\\$");
        holder.diseaseSummary.setText(values[0]);
        holder.diseaseChance.setText(values[1]);
        holder.diseaseDetails.setText(values[2]);

        setChancePriorityColor(holder.diseaseChance);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.diseaseDetails.getVisibility() == View.GONE) {
                    holder.diseaseDetails.setVisibility(View.VISIBLE);
                } else {
                    holder.diseaseDetails.setVisibility(View.GONE);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return diseasesInformation.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView diseaseSummary;
        private TextView diseaseDetails;
        private TextView diseaseChance;

        private ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            diseaseSummary = itemView.findViewById(R.id.disease_summary);
            diseaseDetails = itemView.findViewById(R.id.disease_details);
            diseaseChance = itemView.findViewById(R.id.disease_chance);
        }
    }

    private void setChancePriorityColor(TextView chanceTextView) {
        if (chanceTextView != null) {
            Context context = chanceTextView.getContext();
            String chanceValueBeforePoint = chanceTextView.getText().toString().split(": ")[1].split("\\.")[0];
            if (chanceValueBeforePoint.length() >= 2 && chanceValueBeforePoint.charAt(0) >= MINIMUM_CHANCE) {
                chanceTextView.setTextColor(ContextCompat.getColor(context, R.color.colorChanceRisk));
            } else {
                chanceTextView.setTextColor(ContextCompat.getColor(context, R.color.colorChanceOk));
            }
        }
    }
}
