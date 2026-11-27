package com.szalajdaniel.medicalclinic.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
    @Table(name = "facilities")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Facility {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true, nullable = false)
        private String name;
        private String city;
        private String zipCode;
        private String street;
        private String buildingNumber;

        @ManyToMany(mappedBy = "facilities")
        private Set<Doctor> doctors = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Facility)) return false;
        Facility other = (Facility) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    }
