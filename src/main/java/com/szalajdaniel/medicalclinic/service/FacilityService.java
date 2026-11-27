package com.szalajdaniel.medicalclinic.service;

import com.szalajdaniel.medicalclinic.exception.FacilityAlreadyExistsException;
import com.szalajdaniel.medicalclinic.mapper.FacilityMapper;
import com.szalajdaniel.medicalclinic.model.dto.CreateFacilityDto;
import com.szalajdaniel.medicalclinic.model.dto.FacilityDto;
import com.szalajdaniel.medicalclinic.model.entity.Facility;
import com.szalajdaniel.medicalclinic.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FacilityService {
    private final FacilityRepository facilityRepository;
    private final FacilityMapper facilityMapper;

    @Transactional(readOnly = true)
    public List<FacilityDto> getAllFacilities() {
        return facilityRepository.findAll().stream()
                .map(facilityMapper::toDto)
                .toList();
    }


    public FacilityDto addFacility(CreateFacilityDto dto) {
        log.info("Attempting to add facility with name: {}", dto.name());
        facilityRepository.findByName(dto.name()).ifPresent(f -> {
            throw new FacilityAlreadyExistsException("Facility with name " + dto.name() + " already exists");
        });
        Facility facility = facilityMapper.toFacility(dto);
        facilityRepository.save(facility);
        return facilityMapper.toDto(facility);
    }
}
