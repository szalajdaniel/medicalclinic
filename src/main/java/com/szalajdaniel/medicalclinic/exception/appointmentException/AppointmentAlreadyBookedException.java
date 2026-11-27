package com.szalajdaniel.medicalclinic.exception.appointmentException;

import com.szalajdaniel.medicalclinic.exception.MedicalClinicException;
import org.springframework.http.HttpStatus;

public class AppointmentAlreadyBookedException extends MedicalClinicException {
    public AppointmentAlreadyBookedException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
