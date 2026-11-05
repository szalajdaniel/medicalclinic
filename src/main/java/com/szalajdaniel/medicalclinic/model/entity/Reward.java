package com.szalajdaniel.medicalclinic.model.entity;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reward {
    private Long id;
    private String name;
    private String description;
    @Min(1)
    private Integer PointsCost;
    private Long programId;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
    private Integer AvailableQuantity;

    public boolean isAvailable() {
        LocalDate today = LocalDate.now();
        boolean active = isActive();
        boolean afterStart = getStartDate() == null
                || !today.isBefore(getStartDate());
        boolean beforeEnd = getEndDate() == null
                || !today.isAfter(getEndDate());
        boolean quantityAvailable = getAvailableQuantity() == null
                || getAvailableQuantity() > 0;
        return active && afterStart && beforeEnd && quantityAvailable;
    }
}
