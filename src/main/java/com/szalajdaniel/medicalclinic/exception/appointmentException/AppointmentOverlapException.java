package com.szalajdaniel.medicalclinic.exception.appointmentException;

import com.szalajdaniel.medicalclinic.exception.MedicalClinicException;
import org.springframework.http.HttpStatus;

public class AppointmentOverlapException extends MedicalClinicException {
    public AppointmentOverlapException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
