package com.szalajdaniel.medicalclinic.model.dto;

public record FacilityDto(
        Long id,
        String name,
        String city,
        String zipCode,
        String street,
        String buildingNumber
) {}

