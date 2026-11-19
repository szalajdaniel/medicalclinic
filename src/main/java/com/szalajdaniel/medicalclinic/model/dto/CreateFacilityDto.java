package com.szalajdaniel.medicalclinic.model.dto;

public record CreateFacilityDto(
        String name,
        String city,
        String zipCode,
        String street,
        String buildingNumber)
{}

