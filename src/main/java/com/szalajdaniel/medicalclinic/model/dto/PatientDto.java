package com.szalajdaniel.medicalclinic.model.dto;

import java.time.LocalDate;

public record PatientDto(
        Long id,
        String email,
        String idCardNo,
        String lastName,
        String phoneNumber,
        LocalDate birthday,
        Long userId
) {}
