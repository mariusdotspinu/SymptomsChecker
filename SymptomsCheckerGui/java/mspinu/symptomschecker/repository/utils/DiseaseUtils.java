package mspinu.symptomschecker.repository.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mspinu.symptomschecker.repository.dto.DiseaseDto;
import mspinu.symptomschecker.repository.entities.DiseaseEntity;

public class DiseaseUtils {

    public static List<DiseaseDto> getListOfDtoFromEntities(List<DiseaseEntity> diseaseEntities){
        List<DiseaseDto> listOfDto = new ArrayList<>();
        for (DiseaseEntity entity : diseaseEntities){
            listOfDto.add(mapDiseaseEntityToDto(entity));
        }

        return listOfDto;
    }

    public static DiseaseEntity mapDiseaseDtoToEntity(DiseaseDto diseaseDto){
        DiseaseEntity diseaseEntity = new DiseaseEntity();
        diseaseEntity.setName(diseaseDto.getName());
        diseaseEntity.setSymptoms(getSymptomsAsOneString(diseaseDto.getSymptoms()));

        return diseaseEntity;
    }

    public static DiseaseDto mapDiseaseEntityToDto(DiseaseEntity diseaseEntity){
        DiseaseDto diseaseDto = new DiseaseDto();
        diseaseDto.setName(diseaseEntity.getName());
        diseaseDto.setSymptoms(parseSymptomsString(diseaseEntity.getSymptoms()));

        return diseaseDto;
    }

    public static String getSymptomsAsOneString(List<String> symptomsList){
        StringBuilder builder = new StringBuilder();
        for (String symptom : symptomsList){
            if (!symptom.isEmpty()) {
                builder.append(symptom).append(";");
            }
        }
        if (builder.length() == 0){
            builder.append("None submitted");
        }
        if (builder.charAt(builder.length() - 1) == ';') {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    private static List<String> parseSymptomsString(String symptomsString){
        return new ArrayList<>(Arrays.asList(symptomsString.split(";")));
    }
}
