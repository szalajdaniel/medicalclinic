package com.szalajdaniel.medicalclinic.exception.appointmentException;

import com.szalajdaniel.medicalclinic.exception.MedicalClinicException;
import org.springframework.http.HttpStatus;

public class NotFoundAppointmentException extends MedicalClinicException {
    public NotFoundAppointmentException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
