package com.szalajdaniel.medicalclinic.model.dto;

import java.time.LocalDate;

public record PatientDto(
        Long patientId,
        String email,
        String idCardNo,
        String lastName,
        String phoneNumber,
        LocalDate birthday
) {}
