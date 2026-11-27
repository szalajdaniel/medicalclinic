package com.szalajdaniel.medicalclinic.exception;

import org.springframework.http.HttpStatus;

public class NotFoundPatientException extends MedicalClinicException {
    public NotFoundPatientException(String message){
        super(HttpStatus.NOT_FOUND, message);
    }
}
