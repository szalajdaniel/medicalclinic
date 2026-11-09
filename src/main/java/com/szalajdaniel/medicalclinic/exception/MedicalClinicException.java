package com.szalajdaniel.medicalclinic.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class MedicalClinicException extends RuntimeException {
    private final HttpStatus httpStatus;
    public MedicalClinicException(HttpStatus httpStatus, String message){
        super(message);
        this.httpStatus = httpStatus;
    }
}
