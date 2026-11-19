package com.szalajdaniel.medicalclinic.service;

import com.szalajdaniel.medicalclinic.exception.NotFoundPatientException;
import com.szalajdaniel.medicalclinic.exception.NotFoundUserException;
import com.szalajdaniel.medicalclinic.exception.PatientAlreadyExistsException;
import com.szalajdaniel.medicalclinic.exception.UserAlreadyAssignedException;
import com.szalajdaniel.medicalclinic.mapper.PatientMapper;
import com.szalajdaniel.medicalclinic.model.dto.CreatePatientRequestDto;
import com.szalajdaniel.medicalclinic.model.dto.PatientDto;
import com.szalajdaniel.medicalclinic.model.dto.UpdatePatientDto;
import com.szalajdaniel.medicalclinic.model.entity.Patient;
import com.szalajdaniel.medicalclinic.model.entity.User;
import com.szalajdaniel.medicalclinic.repository.PatientRepository;
import com.szalajdaniel.medicalclinic.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository repository;
    private final UserRepository userRepository;
    private final PatientMapper patientMapper;

    public List<PatientDto> getAllPatients() {
        log.info("Fetching all patients from the repository");
        List<PatientDto> patients = repository.findAll().stream()
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

    @Transactional
    public PatientDto addPatient(CreatePatientRequestDto createPatientRequestDto) {
        log.info("userId z requestu: {}", createPatientRequestDto.userId());
        log.info("Attempting to add a new patient with email: {}", createPatientRequestDto.email());
        repository.findByEmail(createPatientRequestDto.email()).ifPresent(patient -> {
            throw new PatientAlreadyExistsException("Patient with email " + createPatientRequestDto.email() + " already exists");
        });
        repository.findByIdCardNo(createPatientRequestDto.idCardNo()).ifPresent(patient -> {
            throw new PatientAlreadyExistsException("Patient with ID card number " + createPatientRequestDto.idCardNo() + " already exists");
        });
        Patient patient = patientMapper.toPatient(createPatientRequestDto);
        if (createPatientRequestDto.userId() != null) {
            assignUser(patient, createPatientRequestDto.userId());
        }
        repository.save(patient);
        log.info("Successfully added new patient with email: {}", createPatientRequestDto.email());
        return patientMapper.toDto(patient);
    }

    @Transactional
    public void deletePatientByEmail(String email) {
        log.info("Attempting to delete patient with email: {}", email);
        repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundPatientException("Patient with Email " + email + " not found"));
        repository.deleteByEmail(email);
        log.info("Successfully deleted patient with email: {}", email);
    }

    @Transactional
    public PatientDto updatePatientByEmail(String email, UpdatePatientDto updatePatientDto) {
        log.info("Attempting to update patient with email: {}", email);
        Patient patient = repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundPatientException("Patient with Email " + email + " not found"));
        patientMapper.updatePatient(patient, updatePatientDto);
        if (updatePatientDto.userId() != null) {
            assignUser(patient, updatePatientDto.userId());
        }
        log.info("Successfully updated patient with email: {}", email);
        return patientMapper.toDto(patient);
    }

    @Transactional
    public void changePassword(String email, String password) {
        log.info("Attempting to change password for patient with email: {}", email);
        Patient patient = repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundPatientException("Patient with Email " + email + " not found"));
        patient.setPassword(password);
        log.info("Successfully changed password for patient with email: {}", email);
    }

    private void assignUser(Patient patient, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserException("User with Id " + userId + " not found"));
        repository.findByUser_UserId(userId)
                .filter(other -> !other.getPatientId().equals(patient.getPatientId()))
                .ifPresent(other -> {
                    throw new UserAlreadyAssignedException("User with Id " + userId + " is already assigned to another patient");
                });

        patient.setUser(user);
    }
}
