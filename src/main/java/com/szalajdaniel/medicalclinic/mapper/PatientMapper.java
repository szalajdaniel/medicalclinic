package com.szalajdaniel.medicalclinic.mapper;

import com.szalajdaniel.medicalclinic.model.dto.CreatePatientRequestDto;
import com.szalajdaniel.medicalclinic.model.dto.PatientDto;
import com.szalajdaniel.medicalclinic.model.dto.UpdatePatientDto;
import com.szalajdaniel.medicalclinic.model.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    Patient toPatient(CreatePatientRequestDto createPatientRequestDto);
    void updatePatient(@MappingTarget Patient patient, UpdatePatientDto updatePatientDto);
    PatientDto toDto(Patient patient);

}
