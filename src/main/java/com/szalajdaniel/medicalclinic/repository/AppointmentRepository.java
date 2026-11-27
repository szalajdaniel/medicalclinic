package com.szalajdaniel.medicalclinic.repository;

import com.szalajdaniel.medicalclinic.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndStartTimeLessThanAndEndTimeGreaterThan(Long doctorId, LocalDateTime end, LocalDateTime start);
    List<Appointment> findByPatientIsNullAndStartTimeAfter(LocalDateTime now);
    List<Appointment> findByPatientId(Long patientId);

}
