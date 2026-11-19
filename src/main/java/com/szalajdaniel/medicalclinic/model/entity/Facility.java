package com.szalajdaniel.medicalclinic.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

    @Entity
    @Table(name = "facilities")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Facility {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long facilityId;
        @Column(unique = true, nullable = false)
        private String name;
        private String city;
        private String zipCode;
        private String street;
        private String buildingNumber;
    }
