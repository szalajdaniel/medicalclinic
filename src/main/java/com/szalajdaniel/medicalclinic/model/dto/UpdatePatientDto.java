package com.szalajdaniel.medicalclinic.model.dto;

import java.time.LocalDate;

public record UpdatePatientDto(
        String email,
        String lastName,
        String phoneNumber,
        LocalDate birthday
) {}
