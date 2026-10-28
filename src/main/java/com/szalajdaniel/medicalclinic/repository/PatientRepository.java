package com.szalajdaniel.medicalclinic.repository;

import com.szalajdaniel.medicalclinic.model.entity.Patient;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PatientRepository {
    private final List<Patient> patients = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    public PatientRepository() {

        patients.add(new Patient(
                idGenerator.getAndIncrement(),
                "jan.kowalski@gmail.com",
                "123456",
                "ABC123456",
                "Kowalski",
                "500600700",
                LocalDate.of(1990, 5, 15)
        ));
    }
    public List<Patient> getPatients() {
        return new ArrayList<>(patients);
    }

    public void addPatient(Patient patient) {
        patient.setPatientId(idGenerator.getAndIncrement());
        patients.add(patient);
    }

    public void deletePatient(String email) {
        patients.removeIf(p -> p.getEmail().equals(email));
    }

    public Optional<Patient> findByEmail(String email) {
        return patients.stream()
                .filter(patient -> patient.getEmail().equals(email))
                .findFirst();
    }
}
