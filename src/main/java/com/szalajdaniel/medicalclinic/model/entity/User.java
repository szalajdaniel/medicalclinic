package com.szalajdaniel.medicalclinic.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private Long userID;
    private String username;
    private String password;
}
