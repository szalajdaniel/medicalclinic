package com.szalajdaniel.medicalclinic.repository;

import com.szalajdaniel.medicalclinic.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByEmail(String email);
    Optional<Patient> findByIdCardNo(String idCardNo);
    Optional<Patient> findByUser_id(Long userId);
}
