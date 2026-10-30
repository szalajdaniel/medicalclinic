package com.szalajdaniel.medicalclinic.controller;

import com.szalajdaniel.medicalclinic.model.dto.ChangePasswordDto;
import com.szalajdaniel.medicalclinic.model.dto.CreatePatientRequestDto;
import com.szalajdaniel.medicalclinic.model.dto.PatientDto;
import com.szalajdaniel.medicalclinic.model.dto.UpdatePatientDto;
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
    public List<PatientDto> getPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{email}")
    public PatientDto getPatientByEmail(@PathVariable String email) {
        return patientService.getPatientByEmail(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDto addPatient(@RequestBody CreatePatientRequestDto createPatientRequestDto) {
        return patientService.addPatient(createPatientRequestDto);
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable String email) {
        patientService.deletePatientByEmail(email);
    }

    @PutMapping("/{email}")
    public PatientDto updatePatient(@PathVariable String email, @RequestBody UpdatePatientDto updatePatientDto) {
        return patientService.updatePatientByEmail(email, updatePatientDto);
    }

    @PostMapping("/{email}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void chaangePassword(@PathVariable String email, @RequestBody ChangePasswordDto changePasswordDto){
        patientService.changePassword(email, changePasswordDto.password());
    }
}
