package mspinu.symptomschecker.repository.database;

import android.util.Log;

import java.util.List;

import mspinu.symptomschecker.repository.dto.DiseaseDto;
import mspinu.symptomschecker.repository.entities.DiseaseEntity;
import mspinu.symptomschecker.repository.utils.DiseaseUtils;

public abstract class DiseaseDatabaseAccess {

    private static void persistDisease(DiseaseDatabase db, DiseaseEntity diseaseEntity){
        db.diseaseDao().insertAll(diseaseEntity);
        Log.d("PERSIST_DISEASE", db.diseaseDao().getAll().get(0).getName());
    }

    public static void addDisease(DiseaseDatabase db, DiseaseDto diseaseDto){
        persistDisease(db, DiseaseUtils.mapDiseaseDtoToEntity(diseaseDto));
    }

    public static List<DiseaseEntity> getAll(DiseaseDatabase db){
        return db.diseaseDao().getAll();
    }
}
