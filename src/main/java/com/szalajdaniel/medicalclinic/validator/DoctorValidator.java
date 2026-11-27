package com.szalajdaniel.medicalclinic.validator;

import com.szalajdaniel.medicalclinic.exception.DoctorAlreadyExistsException;
import com.szalajdaniel.medicalclinic.exception.NotFoundDoctorException;
import com.szalajdaniel.medicalclinic.model.entity.Doctor;
import com.szalajdaniel.medicalclinic.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DoctorValidator {
    private final DoctorRepository repository;

    public Doctor validateDoctorExist(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundDoctorException("Doctor with id " + id + " not found"));
    }

    public Doctor validateDoctorExist(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundDoctorException("Doctor with email " + email + " not found"));
    }

    public void validateDoctorNotExistByEmail(String email) {
        if (repository.existsByEmail(email)) {
            throw new DoctorAlreadyExistsException("Doctor with email " + email + " already exists");
        }
    }
}