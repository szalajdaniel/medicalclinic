package com.szalajdaniel.medicalclinic.exception;

import org.springframework.http.HttpStatus;

public class NotFoundFacilityException extends MedicalClinicException {
    public NotFoundFacilityException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}

