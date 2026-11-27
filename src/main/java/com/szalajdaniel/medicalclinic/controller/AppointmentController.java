package com.szalajdaniel.medicalclinic.controller;

import com.szalajdaniel.medicalclinic.model.dto.AppointmentDto;
import com.szalajdaniel.medicalclinic.model.dto.BookAppointmentCommand;
import com.szalajdaniel.medicalclinic.model.dto.CreateAppointmentCommand;
import com.szalajdaniel.medicalclinic.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping
    public List<AppointmentDto> getAppointments() {
        log.info("GET /appointments - request received");
        List<AppointmentDto> appointments = appointmentService.getAllAppointments();
        log.info("GET /appointments - responding with HTTP 200 OK, returned: {}", appointments.size());
        return appointments;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDto createAppointment(@RequestBody CreateAppointmentCommand command) {
        log.info("POST /appointments - request received for doctor {}", command.doctorId());
        AppointmentDto created = appointmentService.createAppointment(command);
        log.info("POST /appointments - responding with HTTP 201 CREATED, id: {}", created.id());
        return created;
    }

    @GetMapping("/available")
    public List<AppointmentDto> getAvailable() {
        log.info("GET /appointments/available - request received");
        List<AppointmentDto> appointments = appointmentService.getAvailableAppointments();
        log.info("GET /appointments/available - responding with HTTP 200 OK, returned: {}", appointments.size());
        return appointments;
    }

    @PostMapping("/{id}/book")
    public AppointmentDto bookAppointment(@PathVariable Long id, @RequestBody BookAppointmentCommand command) {
        log.info("POST /appointments/{}/book - request received", id);
        AppointmentDto booked = appointmentService.bookAppointment(id, command);
        log.info("POST /appointments/{}/book - responding with HTTP 200 OK", id);
        return booked;
    }

    @GetMapping("/patient/{patientId}")
    public List<AppointmentDto> getPatientAppointments(@PathVariable Long patientId) {
        log.info("GET /appointments/patient/{} - request received", patientId);
        List<AppointmentDto> appointments = appointmentService.getPatientAppointments(patientId);
        log.info("GET /appointments/patient/{} - responding with HTTP 200 OK, returned: {}", patientId, appointments.size());
        return appointments;
    }
}
