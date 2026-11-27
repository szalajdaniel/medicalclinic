package com.szalajdaniel.medicalclinic.model.dto;

import java.time.LocalDateTime;

public record AppointmentDto(
        Long id,
        Long doctorId,
        Long patientId,
        LocalDateTime startTime,
        LocalDateTime endTime
) {}
