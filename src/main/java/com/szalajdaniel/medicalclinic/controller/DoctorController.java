package com.szalajdaniel.medicalclinic.controller;

import com.szalajdaniel.medicalclinic.model.dto.CreateDoctorDto;
import com.szalajdaniel.medicalclinic.model.dto.DoctorDto;
import com.szalajdaniel.medicalclinic.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping
    public List<DoctorDto> getDoctors() {
        log.info("GET /doctors - request received");
        List<DoctorDto> doctors = doctorService.getAllDoctors();
        log.info("GET /doctors - responding with HTTP 200 OK, doctors returned: {}", doctors.size());
        return doctors;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDto addDoctor(@RequestBody CreateDoctorDto dto) {
        log.info("POST /doctors - request received");
        DoctorDto created = doctorService.addDoctor(dto);
        log.info("POST /doctors - responding with HTTP 201 CREATED, doctorId: {}", created.id());
        return created;
    }

    @PostMapping("/{email}/facilities/{facilityId}")
    public DoctorDto assignToFacility(@PathVariable String email, @PathVariable Long facilityId) {
        log.info("POST /doctors/{}/facilities/{} - request received", email, facilityId);
        DoctorDto doctor = doctorService.assignToFacility(email, facilityId);
        log.info("POST /doctors/{}/facilities/{} - responding with HTTP 200 OK", email, facilityId);
        return doctor;
    }
}
