package com.szalajdaniel.medicalclinic.exception.appointmentException;

import com.szalajdaniel.medicalclinic.exception.MedicalClinicException;
import org.springframework.http.HttpStatus;

public class InvalidAppointmentTimeException extends MedicalClinicException {
    public InvalidAppointmentTimeException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
