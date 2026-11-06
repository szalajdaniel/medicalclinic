package com.szalajdaniel.medicalclinic.controller;

import com.szalajdaniel.medicalclinic.model.dto.ChangePasswordDto;
import com.szalajdaniel.medicalclinic.model.dto.CreatePatientRequestDto;
import com.szalajdaniel.medicalclinic.model.dto.PatientDto;
import com.szalajdaniel.medicalclinic.model.dto.UpdatePatientDto;
import com.szalajdaniel.medicalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public List<PatientDto> getPatients() {
        log.info("Received request to fetch all patients");
        List<PatientDto> patients = patientService.getAllPatients();
        log.info("Successfully fetched {} patients", patients.size());
        return patients;
    }

    @GetMapping("/{email}")
    public PatientDto getPatientByEmail(@PathVariable String email) {
        log.info("Received request to fetch patient by email: {}", email);
        PatientDto patient = patientService.getPatientByEmail(email);
        log.info("Successfully fetched patient with email: {}", email);
        return patient;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDto addPatient(@RequestBody CreatePatientRequestDto createPatientRequestDto) {
        log.info("Received request to add a new patient");
        PatientDto createdPatient = patientService.addPatient(createPatientRequestDto);
        log.info("Successfully added new patient");
        return createdPatient;
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable String email) {
        log.info("Received request to delete patient by email: {}", email);
        patientService.deletePatientByEmail(email);
        log.info("Successfully deleted patient with email: {}", email);
    }

    @PutMapping("/{email}")
    public PatientDto updatePatient(@PathVariable String email, @RequestBody UpdatePatientDto updatePatientDto) {
        log.info("Received request to update patient with email: {}", email);
        PatientDto updatedPatient = patientService.updatePatientByEmail(email, updatePatientDto);
        log.info("Successfully updated patient with email: {}", email);
        return updatedPatient;
    }

    @PostMapping("/{email}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void chaangePassword(@PathVariable String email, @RequestBody ChangePasswordDto changePasswordDto){
        log.info("Received request to change password for patient with email: {}", email);
        patientService.changePassword(email, changePasswordDto.password());
        log.info("Successfully changed password for patient with email: {}", email);
    }
}
