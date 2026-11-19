package com.szalajdaniel.medicalclinic.exception;

import org.springframework.http.HttpStatus;

public class DoctorAlreadyExistsException extends MedicalClinicException {
    public DoctorAlreadyExistsException(String message){
        super(HttpStatus.CONFLICT, message);
    }
}
