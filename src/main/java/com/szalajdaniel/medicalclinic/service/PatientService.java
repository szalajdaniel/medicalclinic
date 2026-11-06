package com.szalajdaniel.medicalclinic.service;

import com.szalajdaniel.medicalclinic.mapper.PatientMapper;
import com.szalajdaniel.medicalclinic.model.dto.CreatePatientRequestDto;
import com.szalajdaniel.medicalclinic.model.dto.PatientDto;
import com.szalajdaniel.medicalclinic.model.dto.UpdatePatientDto;
import com.szalajdaniel.medicalclinic.model.entity.Patient;
import com.szalajdaniel.medicalclinic.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
        Patient patient = repository.findByEmail(email).orElseThrow(() -> {
            log.warn("Patient not found with email: {}", email);
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono pacjenta o danym email");
        });
        log.info("Successfully found patient with email: {}", email);
        return patientMapper.toDto(patient);
    }

    public PatientDto addPatient(CreatePatientRequestDto createPatientRequestDto) {
        log.info("Attempting to add a new patient with email: {}", createPatientRequestDto.email());
        repository.findByEmail(createPatientRequestDto.email()).ifPresent(patient -> {
            log.warn("Patient with email already exists: {}", createPatientRequestDto.email());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Istnieje pacjent o podanym adresie email");
        });
        Patient patient = patientMapper.toPatient(createPatientRequestDto);
        repository.addPatient(patient);
        log.info("Successfully added new patient with email: {}", createPatientRequestDto.email());
        return patientMapper.toDto(patient);
    }

    public void deletePatientByEmail(String email) {
        log.info("Attempting to delete patient with email: {}", email);
        repository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("Patient to delete not found with email: {}", email);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono pacjenta o danym mailu");
                });
        repository.deletePatient(email);
        log.info("Successfully deleted patient with email: {}", email);
    }

    public PatientDto updatePatientByEmail(String email, UpdatePatientDto updatePatientDto) {
        log.info("Attempting to update patient with email: {}", email);
        Patient patient = repository.findByEmail(email).orElseThrow(() -> {
            log.warn("Patient to update not found with email: {}", email);
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono pacjenta o danym mailu");
        });
        patientMapper.updatePatient(patient, updatePatientDto);
        log.info("Successfully updated patient with email: {}", email);
        return patientMapper.toDto(patient);
    }

    public void changePassword(String email, String password) {
        log.info("Attempting to change password for patient with email: {}", email);
        Patient patient = repository.findByEmail(email).orElseThrow(() -> {
            log.warn("Patient to change password not found with email: {}", email);
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono pacjenta o danym mailu");
        });
        patient.setPassword(password);
        log.info("Successfully changed password for patient with email: {}", email);
    }
}
