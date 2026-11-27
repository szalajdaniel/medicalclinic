package com.szalajdaniel.medicalclinic.mapper;

import com.szalajdaniel.medicalclinic.model.dto.CreatePatientCommandDto;
import com.szalajdaniel.medicalclinic.model.dto.PatientDto;
import com.szalajdaniel.medicalclinic.model.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(target = "user", ignore = true)
    Patient toPatient(CreatePatientCommandDto dto);

    @Mapping(source = "user.id", target = "userId")
    PatientDto toDto(Patient patient);

}
