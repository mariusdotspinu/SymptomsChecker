package mspinu.symptomschecker.repository.dto;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class DiseaseDto implements Parcelable{
    private String name;
    private List<String> symptoms = new ArrayList<>();

    public DiseaseDto(){

    }

    protected DiseaseDto(Parcel in) {
        name = in.readString();
        symptoms = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeStringList(symptoms);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DiseaseDto> CREATOR = new Creator<DiseaseDto>() {
        @Override
        public DiseaseDto createFromParcel(Parcel in) {
            return new DiseaseDto(in);
        }

        @Override
        public DiseaseDto[] newArray(int size) {
            return new DiseaseDto[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }
}
