package com.szalajdaniel.medicalclinic.model.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreatePatientCommandDto(
        String email,
        String password,
        String idCardNo,
        String lastName,
        String phoneNumber,
        LocalDate birthday,
        @NotNull Long userId
) {}
