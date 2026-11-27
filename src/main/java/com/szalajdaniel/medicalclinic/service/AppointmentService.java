package com.szalajdaniel.medicalclinic.service;

import com.szalajdaniel.medicalclinic.mapper.AppointmentMapper;
import com.szalajdaniel.medicalclinic.model.dto.AppointmentDto;
import com.szalajdaniel.medicalclinic.model.dto.BookAppointmentCommand;
import com.szalajdaniel.medicalclinic.model.dto.CreateAppointmentCommand;
import com.szalajdaniel.medicalclinic.model.entity.Appointment;
import com.szalajdaniel.medicalclinic.model.entity.Doctor;
import com.szalajdaniel.medicalclinic.model.entity.Patient;
import com.szalajdaniel.medicalclinic.repository.AppointmentRepository;
import com.szalajdaniel.medicalclinic.validator.AppointmentValidator;
import com.szalajdaniel.medicalclinic.validator.DoctorValidator;
import com.szalajdaniel.medicalclinic.validator.PatientValidator;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentValidator appointmentValidator;
    private final DoctorValidator doctorValidator;
    private final PatientValidator patientValidator;
    private final AppointmentMapper mapper;

    @Transactional(readOnly = true)
    public List<AppointmentDto> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public AppointmentDto createAppointment(CreateAppointmentCommand command) {
        log.info("Attempting to create appointment for doctor {}", command.doctorId());
        appointmentValidator.validateTime(command.startTime(), command.endTime());
        Doctor doctor = doctorValidator.validateDoctorExist(command.doctorId());
        appointmentValidator.validateNoOverlap(doctor.getId(), command.startTime(), command.endTime());
        Appointment saved = appointmentRepository.save(new Appointment(doctor, command.startTime(), command.endTime()));
        log.info("Successfully created appointment {}", saved.getId());
        return mapper.toDto(saved);
    }

    @Transactional
    public AppointmentDto bookAppointment(Long appointmentId, BookAppointmentCommand command) {
        log.info("Attempting to book appointment {} for patient {}", appointmentId, command.patientId());
        Appointment appointment = appointmentValidator.validateAppointmentExist(appointmentId);
        Patient patient = patientValidator.validatePatientExist(command.patientId());
        appointmentValidator.validateCanBook(appointment);
        appointment.book(patient);
        Appointment saved = appointmentRepository.save(appointment);
        log.info("Successfully booked appointment {}", appointmentId);
        return mapper.toDto(saved);
    }

    public List<AppointmentDto> getAvailableAppointments() {
        return appointmentRepository.findByPatientIsNullAndStartTimeAfter(LocalDateTime.now()).stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<AppointmentDto> getPatientAppointments(Long patientId) {
        patientValidator.validatePatientExist(patientId);
        return appointmentRepository.findByPatientId(patientId).stream()
                .map(mapper::toDto)
                .toList();
    }
}
