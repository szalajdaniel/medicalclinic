package com.szalajdaniel.medicalclinic.service;

import com.szalajdaniel.medicalclinic.mapper.PatientMapper;
import com.szalajdaniel.medicalclinic.model.dto.CreatePatientRequestDto;
import com.szalajdaniel.medicalclinic.model.dto.PatientDto;
import com.szalajdaniel.medicalclinic.model.dto.UpdatePatientDto;
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

    public List<PatientDto> getAllPatients(){
        return repository.getPatients().stream()
                .map(mapper::toDto)
                .toList();
    }

    public PatientDto getPatientByEmail(String email){
        Patient patient = repository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Nie znaleziono pacjenta o danym email"
                ));
        return mapper.toDto(patient);
    }

    public PatientDto addPatient(CreatePatientRequestDto createPatientRequestDto){
        repository.findByEmail(createPatientRequestDto.email())
                .ifPresent(patient -> {
                            throw new ResponseStatusException(HttpStatus.CONFLICT, "Istnieje pacjent o podanym adresie email");
                        });
        Patient patient = mapper.toPatient(createPatientRequestDto);
        repository.addPatient(patient);
        return mapper.toDto(patient);
    }

    public void deletePatientByEmail(String email){
        repository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono pacjenta o danym mailu"));
        repository.deletePatient(email);
    }

    public PatientDto updatePatientByEmail(String email, UpdatePatientDto updatePatientDto){
        Patient patient = repository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Nie znaleziono pacjenta o podanym adresie email"
                    ));
            mapper.updatePatient(patient, updatePatientDto);
            return mapper.toDto(patient);
    }

    public void changePassword(String email, String password){
        Patient patient = repository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Nie znaleziono pacjenta o podanym mailu"
                ));

        patient.setPassword(password);
    }
}
