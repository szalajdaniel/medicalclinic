package com.szalajdaniel.medicalclinic.validator;

import com.szalajdaniel.medicalclinic.exception.NotFoundPatientException;
import com.szalajdaniel.medicalclinic.exception.NotFoundUserException;
import com.szalajdaniel.medicalclinic.exception.PatientAlreadyExistsException;
import com.szalajdaniel.medicalclinic.exception.UserAlreadyAssignedException;
import com.szalajdaniel.medicalclinic.model.entity.Patient;
import com.szalajdaniel.medicalclinic.model.entity.User;
import com.szalajdaniel.medicalclinic.repository.PatientRepository;
import com.szalajdaniel.medicalclinic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PatientValidator {
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public Patient validatePatientExist(String email) {
        return patientRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundPatientException("Patient with Email " + email + " not found"));
    }
    public Patient validatePatientExist(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundPatientException("Patient with id " + id + " not found"));
    }

    public void validatePatientNotExistByEmail(String email) {
        patientRepository.findByEmail(email).ifPresent(p -> {
            throw new PatientAlreadyExistsException("Patient with email " + email + " already exists");
        });
    }

    public void validatePatientNotExistByIdCardNo(String idCardNo) {
        patientRepository.findByIdCardNo(idCardNo).ifPresent(p -> {
            throw new PatientAlreadyExistsException("Patient with ID card number " + idCardNo + " already exists");
        });
    }


    public User validateUserCanBeAssigned(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserException("User with Id " + userId + " not found"));
        patientRepository.findByUser_id(userId).ifPresent(other -> {
            throw new UserAlreadyAssignedException("User with Id " + userId + " is already assigned to another patient");
        });
        return user;
    }
}



