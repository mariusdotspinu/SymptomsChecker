package mspinu.symptomschecker.repository.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "DISEASE")
public class DiseaseEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "NAME")
    private String name;

    @ColumnInfo(name = "SYMPTOMS")
    private String symptoms;

    @ColumnInfo(name = "TIMESTAMP")
    private String timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
