package com.szalajdaniel.medicalclinic.service;

import com.szalajdaniel.medicalclinic.exception.FacilityAlreadyExistsException;
import com.szalajdaniel.medicalclinic.mapper.FacilityMapper;
import com.szalajdaniel.medicalclinic.model.dto.CreateFacilityDto;
import com.szalajdaniel.medicalclinic.model.dto.FacilityDto;
import com.szalajdaniel.medicalclinic.model.entity.Facility;
import com.szalajdaniel.medicalclinic.repository.FacilityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FacilityService {
    private final FacilityRepository repository;
    private final FacilityMapper mapper;

    public List<FacilityDto> getAllFacilities() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional
    public FacilityDto addFacility(CreateFacilityDto dto) {
        log.info("Attempting to add facility with name: {}", dto.name());
        repository.findByName(dto.name()).ifPresent(f -> {
            throw new FacilityAlreadyExistsException("Facility with name " + dto.name() + " already exists");
        });
        Facility facility = mapper.toFacility(dto);
        repository.save(facility);
        return mapper.toDto(facility);
    }
}
