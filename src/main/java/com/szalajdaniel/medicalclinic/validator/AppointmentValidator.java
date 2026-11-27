package com.szalajdaniel.medicalclinic.validator;

import com.szalajdaniel.medicalclinic.exception.appointmentException.AppointmentAlreadyBookedException;
import com.szalajdaniel.medicalclinic.exception.appointmentException.AppointmentOverlapException;
import com.szalajdaniel.medicalclinic.exception.appointmentException.InvalidAppointmentTimeException;
import com.szalajdaniel.medicalclinic.exception.appointmentException.NotFoundAppointmentException;
import com.szalajdaniel.medicalclinic.model.entity.Appointment;
import com.szalajdaniel.medicalclinic.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AppointmentValidator {
    private final AppointmentRepository repository;

    public Appointment validateAppointmentExist(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundAppointmentException("Appointment with id " + id + " not found"));
    }

    public void validateTime(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new InvalidAppointmentTimeException("Start and end time are required");
        }
        if (!start.isAfter(LocalDateTime.now())) {
            throw new InvalidAppointmentTimeException("Appointment cannot start in the past");
        }
        if (!end.isAfter(start)) {
            throw new InvalidAppointmentTimeException("End time must be after start time");
        }
        if (!isFullQuarter(start) || !isFullQuarter(end)) {
            throw new InvalidAppointmentTimeException("Appointment must start and end at a full quarter of an hour");
        }
    }

    public void validateNoOverlap(Long doctorId, LocalDateTime start, LocalDateTime end) {
        if (repository.existsByDoctorIdAndStartTimeLessThanAndEndTimeGreaterThan(doctorId, end, start)) {
            throw new AppointmentOverlapException("Doctor already has an appointment overlapping this time");
        }
    }

    public void validateCanBook(Appointment appointment) {
        if (!appointment.getStartTime().isAfter(LocalDateTime.now())) {
            throw new InvalidAppointmentTimeException("Cannot book an appointment in the past");
        }
        if (appointment.isBooked()) {
            throw new AppointmentAlreadyBookedException("Appointment " + appointment.getId() + " is already booked");
        }
    }

    private boolean isFullQuarter(LocalDateTime time) {
        return time.getMinute() % 15 == 0 && time.getSecond() == 0 && time.getNano() == 0;
    }
}
