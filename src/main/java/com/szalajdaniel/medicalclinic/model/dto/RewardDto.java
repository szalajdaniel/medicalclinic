package com.szalajdaniel.medicalclinic.model.dto;

import java.time.LocalDate;

public record RewardDto(
        Long id,
        String name,
        String description,
        Integer pointsCost,
        Long programId,
        LocalDate startDate,
        LocalDate endDate,
        boolean active,
        Integer availableQuantity,
        boolean available
) {}
