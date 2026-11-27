package com.szalajdaniel.medicalclinic.service;

import com.szalajdaniel.medicalclinic.mapper.PageMapper;
import com.szalajdaniel.medicalclinic.mapper.PatientMapper;
import com.szalajdaniel.medicalclinic.model.dto.CreatePatientCommandDto;
import com.szalajdaniel.medicalclinic.model.dto.PageDto;
import com.szalajdaniel.medicalclinic.model.dto.PatientDto;
import com.szalajdaniel.medicalclinic.model.dto.UpdatePatientCommandDto;
import com.szalajdaniel.medicalclinic.model.entity.Patient;
import com.szalajdaniel.medicalclinic.model.entity.User;
import com.szalajdaniel.medicalclinic.repository.PatientRepository;
import com.szalajdaniel.medicalclinic.validator.PatientValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final PatientValidator patientValidator;

    public PageDto<PatientDto> getAllPatients(Pageable pageable) {
        log.info("Fetching all patients from the repository");
        Page<PatientDto> patients = patientRepository.findAll(pageable)
                .map(patientMapper::toDto);
        log.info("Successfully fetched {} patients", patients.getTotalElements());
        return PageMapper.toPageDto(patients);
    }

    public PatientDto getByEmail(String email) {
        log.info("Fetching patient by email: {}", email);
        Patient patient = patientValidator.validatePatientExist(email);
        return patientMapper.toDto(patient);
    }

    public PatientDto addPatient(CreatePatientCommandDto createPatientCommandDto) {
        log.info("Attempting to add a new patient with email: {}", createPatientCommandDto.email());
        patientValidator.validatePatientNotExistByEmail(createPatientCommandDto.email());
        patientValidator.validatePatientNotExistByIdCardNo(createPatientCommandDto.idCardNo());
        User user = patientValidator.validateUserCanBeAssigned(createPatientCommandDto.userId());
        Patient patient = patientMapper.toPatient(createPatientCommandDto);
        patient.setUser(user);
        patientRepository.save(patient);
        log.info("Successfully added new patient with email: {}", createPatientCommandDto.email());
        return patientMapper.toDto(patient);
    }

    public void deletePatientByEmail(String email) {
        log.info("Attempting to delete patient with email: {}", email);
        Patient patient = patientValidator.validatePatientExist(email);
        patientRepository.delete(patient);
        log.info("Successfully deleted patient with email: {}", email);
    }

    @Transactional
    public PatientDto updatePatientByEmail(String email, UpdatePatientCommandDto updatePatientCommandDto) {
        Patient patient = patientValidator.validatePatientExist(email);
        if (!patient.getEmail().equals(updatePatientCommandDto.email())) {
            patientValidator.validatePatientNotExistByEmail(updatePatientCommandDto.email());
        }
        patient.update(updatePatientCommandDto);
        return patientMapper.toDto(patient);
    }

    @Transactional
    public void changePassword(String email, String password) {
        log.info("Attempting to change password for patient with email: {}", email);
        Patient patient = patientValidator.validatePatientExist(email);
        patient.changePassword(password);
        log.info("Successfully changed password for patient with email: {}", email);
    }
}
