package com.szalajdaniel.medicalclinic.exception;

import com.szalajdaniel.medicalclinic.model.dto.ErrorMessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MedicalClinicException.class)
    public ResponseEntity <ErrorMessageDto> handleMedicalException(MedicalClinicException medicalClinicException){
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(medicalClinicException.getMessage(), medicalClinicException.getHttpStatus());
        return ResponseEntity.status(medicalClinicException.getHttpStatus()).body(errorMessageDto);
    }
}
