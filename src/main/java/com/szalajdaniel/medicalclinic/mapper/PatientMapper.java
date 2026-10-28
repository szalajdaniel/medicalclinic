package com.szalajdaniel.medicalclinic.mapper;

import com.szalajdaniel.medicalclinic.model.dto.CreatePatientRequest;
import com.szalajdaniel.medicalclinic.model.dto.PatientDto;
import com.szalajdaniel.medicalclinic.model.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public Patient toPatient(CreatePatientRequest request) {
        Patient patient = new Patient();

        patient.setEmail(request.email());
        patient.setPassword(request.password());
        patient.setIdCardNo(request.idCardNo());
        patient.setLastName(request.lastName());
        patient.setPhoneNumber(request.phoneNumber());
        patient.setBirthday(request.birthday());

        return patient;
    }
    public void updatePatient(
            Patient patient,
            CreatePatientRequest request
    ) {
        patient.setEmail(request.email());
        patient.setPassword(request.password());
        patient.setIdCardNo(request.idCardNo());
        patient.setLastName(request.lastName());
        patient.setPhoneNumber(request.phoneNumber());
        patient.setBirthday(request.birthday());
    }

    public PatientDto toDto(Patient patient) {
        return new PatientDto(
                patient.getPatientId(),
                patient.getEmail(),
                patient.getIdCardNo(),
                patient.getLastName(),
                patient.getPhoneNumber(),
                patient.getBirthday()
        );
    }
}
