package mspinu.symptomschecker.business.process.facade;

import java.util.List;

import mspinu.symptomschecker.repository.dto.DiseaseDto;

public interface DiseaseFacade {
    public void addDisease(DiseaseDto diseaseDto);

    public List<DiseaseDto> getAllDiseasesFromDb();
}
