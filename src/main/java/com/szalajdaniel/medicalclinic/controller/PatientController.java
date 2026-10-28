package com.szalajdaniel.medicalclinic.controller;

import com.szalajdaniel.medicalclinic.model.dto.CreatePatientRequest;
import com.szalajdaniel.medicalclinic.model.dto.PatientDto;
import com.szalajdaniel.medicalclinic.model.entity.Patient;
import com.szalajdaniel.medicalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")

@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    List<Patient> getPatients(){
        return patientService.getAllPatients();
    }
    @GetMapping("/{email}")
    public PatientDto getPatientByEmail(@PathVariable String email){
        return patientService.getPatientByEmail(email);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDto addPatient(@RequestBody CreatePatientRequest createPatientRequest){
        return patientService.addPatient(createPatientRequest);
    }
    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable String email){
        patientService.deletePatientByEmail(email);
    }
    @PutMapping("/{email}")
    public PatientDto updatePatient(@PathVariable String email, @RequestBody CreatePatientRequest createPatientRequest){
       return patientService.updatePatientByEmail(email, createPatientRequest);

    }
}
