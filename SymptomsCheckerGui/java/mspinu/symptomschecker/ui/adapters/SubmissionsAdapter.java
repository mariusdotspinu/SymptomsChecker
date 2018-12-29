package mspinu.symptomschecker.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mspinu.symptomschecker.R;
import mspinu.symptomschecker.repository.dto.DiseaseDto;
import mspinu.symptomschecker.repository.utils.DiseaseUtils;

public class SubmissionsAdapter extends RecyclerView.Adapter<SubmissionsAdapter.ViewHolder> {
    private List<DiseaseDto> diseaseDtoList;

    public SubmissionsAdapter(){
        diseaseDtoList = new ArrayList<>();
    }

    public void setSubmissions(List<DiseaseDto> diseaseDtoList) {
        this.diseaseDtoList = diseaseDtoList;
    }

    public List<DiseaseDto> getSubmissions() {
        return this.diseaseDtoList;
    }

    @Override
    public SubmissionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.submission_layout,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SubmissionsAdapter.ViewHolder holder, int position) {
        holder.diseaseName.setText(diseaseDtoList.get(position).getName());
        List<String> symptoms = diseaseDtoList.get(position).getSymptoms();
        holder.diseaseSymptoms.setText(DiseaseUtils.getSymptomsAsOneString(symptoms));
    }

    @Override
    public int getItemCount() {
        return diseaseDtoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView diseaseName;
        TextView diseaseSymptoms;
        private ViewHolder(View itemView) {
            super(itemView);
            diseaseName = itemView.findViewById(R.id.disease_name);
            diseaseSymptoms = itemView.findViewById(R.id.disease_symptoms);
        }
    }

}

