package com.szalajdaniel.medicalclinic.exception;

import org.springframework.http.HttpStatus;

public class FacilityAlreadyExistsException extends MedicalClinicException {
    public FacilityAlreadyExistsException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
