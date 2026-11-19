package com.szalajdaniel.medicalclinic.exception;

import org.springframework.http.HttpStatus;

public class NotFoundDoctorException extends MedicalClinicException {
    public NotFoundDoctorException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}