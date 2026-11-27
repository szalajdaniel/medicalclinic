package com.szalajdaniel.medicalclinic.model.dto;

import java.time.LocalDateTime;

public record CreateAppointmentCommand(
        Long doctorId,
        LocalDateTime startTime,
        LocalDateTime endTime
) {}
