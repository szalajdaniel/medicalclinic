package com.szalajdaniel.medicalclinic.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyAssignedException extends MedicalClinicException {
    public UserAlreadyAssignedException(String message){
        super(HttpStatus.CONFLICT, message);
    }
}
