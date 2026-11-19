package com.szalajdaniel.medicalclinic.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record CreateRewardCommandDto(
        @NotBlank
        String name,
        String description,

        @Min(1)
        Integer pointsCost,
        LocalDate startDate,
        LocalDate endDate,
        boolean active,

        @Min(0)
        Integer availableQuantity
) {}
