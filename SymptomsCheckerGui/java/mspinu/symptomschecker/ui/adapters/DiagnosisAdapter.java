package mspinu.symptomschecker.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mspinu.symptomschecker.R;

public class DiagnosisAdapter extends RecyclerView.Adapter<DiagnosisAdapter.ViewHolder>{
    private List<String> diagnosisSymptoms;

    public DiagnosisAdapter(){
        this.diagnosisSymptoms = new ArrayList<>();
    }

    public void setDiagnosisSymptoms(ArrayList<String> diagnosisSymptoms) {
        this.diagnosisSymptoms = diagnosisSymptoms;
    }

    public List<String> getDiagnosisSymptoms(){
        return this.diagnosisSymptoms;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.diagnosis_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.symptom.setText(diagnosisSymptoms.get(position));
        holder.symptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diagnosisSymptoms.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return diagnosisSymptoms.size();
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(this.diagnosisSymptoms.get(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView symptom;
        private ViewHolder(View itemView) {
            super(itemView);
            symptom = itemView.findViewById(R.id.symptom);
        }
    }
}
