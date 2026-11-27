package com.szalajdaniel.medicalclinic.controller;

import com.szalajdaniel.medicalclinic.model.dto.*;
import com.szalajdaniel.medicalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public PageDto<PatientDto> getPatients(@PageableDefault(size = 10, sort = "id")Pageable pageable) {
        log.info("GET /patients received");
        PageDto<PatientDto> patients = patientService.getAllPatients(pageable);
        log.info("GET /patients - responding with HTTP 200 OK, patients returned: {}", patients.totalElements());
        return patients;
    }

    @GetMapping("/{email}")
    public PatientDto getPatientByEmail(@PathVariable String email) {
        log.info("GET /patients/{} - request received", email);
        PatientDto patient = patientService.getByEmail(email);
        log.info("GET /patients/{} - responding with HTTP 200 OK", email);
        return patient;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDto addPatient(@RequestBody CreatePatientCommandDto createPatientCommandDto) {
        log.info("POST /patients - request received");
        PatientDto createdPatient = patientService.addPatient(createPatientCommandDto);
        log.info("POST /patients - responding with HTTP 201 CREATED, patientId: {}", createdPatient.id());
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
    public PatientDto updatePatient(@PathVariable String email, @RequestBody UpdatePatientCommandDto updatePatientCommandDto) {
        log.info("PUT /patients/{} - request received", email);
        PatientDto updatedPatient = patientService.updatePatientByEmail(email, updatePatientCommandDto);
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
