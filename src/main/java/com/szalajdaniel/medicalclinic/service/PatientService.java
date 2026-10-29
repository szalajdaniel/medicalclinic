package com.szalajdaniel.medicalclinic.service;

import com.szalajdaniel.medicalclinic.mapper.PatientMapper;
import com.szalajdaniel.medicalclinic.model.dto.CreatePatientRequest;
import com.szalajdaniel.medicalclinic.model.dto.PatientDto;
import com.szalajdaniel.medicalclinic.model.entity.Patient;
import com.szalajdaniel.medicalclinic.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository repository;
    private final PatientMapper mapper;
    public List<Patient> getAllPatients(){
        return repository.getPatients();
    }

    public PatientDto getPatientByEmail(String email){
        Patient patient = repository.findByEmail(email)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Nie znaleziono pacjenta o danym email"
                ));


        return mapper.toDto(patient);
    }
    public PatientDto addPatient(CreatePatientRequest createPatientRequest){
        repository.findByEmail(createPatientRequest.email())
                .ifPresent(patient -> {
                            throw new ResponseStatusException(HttpStatus.CONFLICT, "Istnieje pacjent o podanym adresie email");
                        });
        Patient patient = mapper.toPatient(createPatientRequest);
        repository.addPatient(patient);
        return mapper.toDto(patient);
    }
    public void deletePatientByEmail(String email){
        repository.findByEmail(email)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono pacjenta o danym mailu"));
        repository.deletePatient(email);
    }

    public PatientDto updatePatientByEmail(String email, CreatePatientRequest createPatientRequest){
        Patient patient = repository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Pacjent o podanym adresie email już istnieje"
                    ));

            mapper.updatePatient(patient, createPatientRequest);

            return mapper.toDto(patient);


    }
}
