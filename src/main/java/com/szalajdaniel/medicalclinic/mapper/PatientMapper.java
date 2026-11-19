package com.szalajdaniel.medicalclinic.mapper;

import com.szalajdaniel.medicalclinic.model.dto.CreatePatientRequestDto;
import com.szalajdaniel.medicalclinic.model.dto.PatientDto;
import com.szalajdaniel.medicalclinic.model.dto.UpdatePatientDto;
import com.szalajdaniel.medicalclinic.model.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(target = "user", ignore = true)
    Patient toPatient(CreatePatientRequestDto createPatientRequestDto);
    void updatePatient(@MappingTarget Patient patient, UpdatePatientDto updatePatientDto);
    @Mapping(source = "user.userId", target = "userId")
    PatientDto toDto(Patient patient);

}
