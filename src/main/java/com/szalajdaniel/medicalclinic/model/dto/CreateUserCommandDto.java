package com.szalajdaniel.medicalclinic.model.dto;

public record CreateUserCommandDto(
        String username,
        String password
) {
}
