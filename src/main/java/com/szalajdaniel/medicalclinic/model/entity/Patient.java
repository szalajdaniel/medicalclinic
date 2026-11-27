package com.szalajdaniel.medicalclinic.model.entity;

import com.szalajdaniel.medicalclinic.model.dto.UpdatePatientCommandDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @Column(unique = true, nullable = false)
    private String idCardNo;
    private String lastName;
    private String phoneNumber;
    private LocalDate birthday;

    @OneToOne(optional = false)
    @JoinColumn(nullable = false, unique = true)
    private User user;

    public void update(UpdatePatientCommandDto updatePatientCommandDto) {
        this.email = updatePatientCommandDto.email();
        this.lastName = updatePatientCommandDto.lastName();
        this.phoneNumber = updatePatientCommandDto.phoneNumber();
        this.birthday = updatePatientCommandDto.birthday();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient other = (Patient) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}
