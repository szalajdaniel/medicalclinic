package com.szalajdaniel.medicalclinic.model.dto;

public record CreateDoctorDto(
        String email,
        String password,
        String firstName,
        String lastName,
        String specialization)
{}

