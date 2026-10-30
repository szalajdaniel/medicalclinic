package com.szalajdaniel.medicalclinic.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private Long patientId;
    private String email;
    private String password;
    private String idCardNo;
    private String lastName;
    private String phoneNumber;
    private LocalDate birthday;
}
