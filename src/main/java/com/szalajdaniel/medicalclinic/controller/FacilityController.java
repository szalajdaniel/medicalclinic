package com.szalajdaniel.medicalclinic.controller;

import com.szalajdaniel.medicalclinic.model.dto.CreateFacilityDto;
import com.szalajdaniel.medicalclinic.model.dto.FacilityDto;
import com.szalajdaniel.medicalclinic.service.FacilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/facilities")
@RequiredArgsConstructor
public class FacilityController {
    private final FacilityService facilityService;

    @GetMapping
    public List<FacilityDto> getFacilities() {
        log.info("GET /facilities - request received");
        List<FacilityDto> facilities = facilityService.getAllFacilities();
        log.info("GET /facilities - responding with HTTP 200 OK, facilities returned: {}", facilities.size());
        return facilities;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FacilityDto addFacility(@RequestBody CreateFacilityDto dto) {
        log.info("POST /facilities - request received");
        FacilityDto created = facilityService.addFacility(dto);
        log.info("POST /facilities - responding with HTTP 201 CREATED, facilityId: {}", created.id());
        return created;
    }
}
