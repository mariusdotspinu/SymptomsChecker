package mspinu.symptomschecker.repository.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import mspinu.symptomschecker.repository.entities.DiseaseEntity;

@Dao
public interface DiseaseDao {
    @Query("SELECT * FROM DISEASE")
    List<DiseaseEntity> getAll();

    @Insert
    void insertAll(DiseaseEntity... diseaseEntities);
}
