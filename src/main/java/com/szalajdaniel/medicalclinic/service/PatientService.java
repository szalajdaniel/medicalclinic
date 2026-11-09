package com.szalajdaniel.medicalclinic.service;

import com.szalajdaniel.medicalclinic.exception.NotFoundPatientException;
import com.szalajdaniel.medicalclinic.exception.PatientAlreadyExistsException;
import com.szalajdaniel.medicalclinic.mapper.PatientMapper;
import com.szalajdaniel.medicalclinic.model.dto.CreatePatientRequestDto;
import com.szalajdaniel.medicalclinic.model.dto.PatientDto;
import com.szalajdaniel.medicalclinic.model.dto.UpdatePatientDto;
import com.szalajdaniel.medicalclinic.model.entity.Patient;
import com.szalajdaniel.medicalclinic.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository repository;
    private final PatientMapper patientMapper;

    public List<PatientDto> getAllPatients() {
        log.info("Fetching all patients from the repository");
        List<PatientDto> patients = repository.getPatients().stream()
                .map(patientMapper::toDto)
                .toList();
        log.info("Successfully fetched {} patients", patients.size());
        return patients;

    }

    public PatientDto getPatientByEmail(String email) {
        log.info("Fetching patient by email: {}", email);
        Patient patient = repository.findByEmail(email).orElseThrow(() -> new NotFoundPatientException("Patient with Email " + email + " not found"));
        log.info("Successfully found patient with email: {}", email);
        return patientMapper.toDto(patient);
    }

    public PatientDto addPatient(CreatePatientRequestDto createPatientRequestDto) {
        log.info("Attempting to add a new patient with email: {}", createPatientRequestDto.email());
        repository.findByEmail(createPatientRequestDto.email()).ifPresent(patient -> {
            throw new PatientAlreadyExistsException("Patient with email " + createPatientRequestDto.email() + " already exists");});
        repository.findByIdCardNo(createPatientRequestDto.idCardNo()).ifPresent(patient -> {
            throw new PatientAlreadyExistsException("Patient with ID card number " + createPatientRequestDto.idCardNo() + " already exists");});
        Patient patient = patientMapper.toPatient(createPatientRequestDto);
        repository.addPatient(patient);
        log.info("Successfully added new patient with email: {}", createPatientRequestDto.email());
        return patientMapper.toDto(patient);
    }

    public void deletePatientByEmail(String email) {
        log.info("Attempting to delete patient with email: {}", email);
        repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundPatientException("Patient with Email " + email + " not found"));
        repository.deletePatient(email);
        log.info("Successfully deleted patient with email: {}", email);
    }

    public PatientDto updatePatientByEmail(String email, UpdatePatientDto updatePatientDto) {
        log.info("Attempting to update patient with email: {}", email);
        Patient patient = repository.findByEmail(email).orElseThrow(() -> new NotFoundPatientException("Patient with Email " + email + " not found"));
            patientMapper.updatePatient(patient, updatePatientDto);
            log.info("Successfully updated patient with email: {}", email);
            return patientMapper.toDto(patient);
    }

    public void changePassword(String email, String password) {
        log.info("Attempting to change password for patient with email: {}", email);
        Patient patient = repository.findByEmail(email).orElseThrow(() -> new NotFoundPatientException("Patient with Email " + email + " not found"));
        patient.setPassword(password);
        log.info("Successfully changed password for patient with email: {}", email);
    }
}
