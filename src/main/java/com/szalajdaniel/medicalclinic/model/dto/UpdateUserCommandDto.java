package com.szalajdaniel.medicalclinic.model.dto;

public record UpdateUserCommandDto(
        String username,
        String password
) {
}
