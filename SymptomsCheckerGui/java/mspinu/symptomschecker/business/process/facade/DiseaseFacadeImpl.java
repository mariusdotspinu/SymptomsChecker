package mspinu.symptomschecker.business.process.facade;

import android.content.Context;

import java.util.List;

import mspinu.symptomschecker.repository.database.DiseaseDatabase;
import mspinu.symptomschecker.repository.database.DiseaseDatabaseAccess;
import mspinu.symptomschecker.repository.dto.DiseaseDto;
import mspinu.symptomschecker.repository.utils.DiseaseUtils;

public class DiseaseFacadeImpl implements DiseaseFacade {

    private DiseaseDatabase db;
    public DiseaseFacadeImpl(Context context){
        this.db = DiseaseDatabase.getDiseaseDatabase(context);
    }

    @Override
    public void addDisease(DiseaseDto disease) {
        DiseaseDatabaseAccess.addDisease(db, disease);
    }

    @Override
    public List<DiseaseDto> getAllDiseasesFromDb() {
        return DiseaseUtils.getListOfDtoFromEntities(DiseaseDatabaseAccess.getAll(db));
    }
}
