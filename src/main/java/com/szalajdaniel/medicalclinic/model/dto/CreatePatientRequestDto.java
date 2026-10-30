package com.szalajdaniel.medicalclinic.model.dto;

import java.time.LocalDate;

public record CreatePatientRequestDto(
        String email,
        String password,
        String idCardNo,
        String lastName,
        String phoneNumber,
        LocalDate birthday
) {}
