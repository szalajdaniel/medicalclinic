package com.szalajdaniel.medicalclinic.exception;

import org.springframework.http.HttpStatus;

public class NotFoundUserException extends MedicalClinicException {
    public NotFoundUserException(String message){
        super(HttpStatus.NOT_FOUND, message);
    }
}
