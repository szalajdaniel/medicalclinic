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
        log.info("GET /patients received");
        List<PatientDto> patients = patientService.getAllPatients();
        log.info("GET /patients - responding with HTTP 200 OK, patients returned: {}", patients.size());
        return patients;
    }

    @GetMapping("/{email}")
    public PatientDto getPatientByEmail(@PathVariable String email) {
        log.info("GET /patients/{} - request received", email);
        PatientDto patient = patientService.getPatientByEmail(email);
        log.info("GET /patients/{} - responding with HTTP 200 OK", email);
        return patient;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDto addPatient(@RequestBody CreatePatientRequestDto createPatientRequestDto) {
        log.info("POST /patients - request received");
        PatientDto createdPatient = patientService.addPatient(createPatientRequestDto);
        log.info("POST /patients - responding with HTTP 201 CREATED, patientId: {}", createdPatient.patientId());
        return createdPatient;
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable String email) {
        log.info("DELETE /patients/{} - request received", email);
        patientService.deletePatientByEmail(email);
        log.info("DELETE /patients/{} - responding with HTTP 204 NO CONTENT", email);
    }

    @PutMapping("/{email}")
    public PatientDto updatePatient(@PathVariable String email, @RequestBody UpdatePatientDto updatePatientDto) {
        log.info("PUT /patients/{} - request received", email);
        PatientDto updatedPatient = patientService.updatePatientByEmail(email, updatePatientDto);
        log.info("PUT /patients/{} - responding with HTTP 200 OK", email);
        return updatedPatient;
    }

    @PostMapping("/{email}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void chaangePassword(@PathVariable String email, @RequestBody ChangePasswordDto changePasswordDto){
        log.info("POST /patients/{}/password - request received", email);
        patientService.changePassword(email, changePasswordDto.password());
        log.info("POST /patients/{}/password - responding with HTTP 204 NO CONTENT", email);
    }
}
