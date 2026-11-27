package com.szalajdaniel.medicalclinic.model.dto;

import java.time.LocalDate;

public record UpdatePatientCommandDto(
        String email,
        String lastName,
        String phoneNumber,
        LocalDate birthday
) {}
