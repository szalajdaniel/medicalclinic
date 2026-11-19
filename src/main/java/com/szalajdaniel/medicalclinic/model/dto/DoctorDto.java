package com.szalajdaniel.medicalclinic.model.dto;

import java.util.Set;

public record DoctorDto(
        Long doctorId,
        String email,
        String firstName,
        String lastName,
        String specialization,
        Set<FacilityDto> facilities)
{}