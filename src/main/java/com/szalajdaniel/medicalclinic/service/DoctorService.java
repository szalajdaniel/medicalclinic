package com.szalajdaniel.medicalclinic.service;

import com.szalajdaniel.medicalclinic.exception.DoctorAlreadyExistsException;
import com.szalajdaniel.medicalclinic.exception.NotFoundDoctorException;
import com.szalajdaniel.medicalclinic.exception.NotFoundFacilityException;
import com.szalajdaniel.medicalclinic.mapper.DoctorMapper;
import com.szalajdaniel.medicalclinic.model.dto.CreateDoctorDto;
import com.szalajdaniel.medicalclinic.model.dto.DoctorDto;
import com.szalajdaniel.medicalclinic.model.entity.Doctor;
import com.szalajdaniel.medicalclinic.model.entity.Facility;
import com.szalajdaniel.medicalclinic.repository.DoctorRepository;
import com.szalajdaniel.medicalclinic.repository.FacilityRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository repository;
    private final FacilityRepository facilityRepository;
    private final DoctorMapper mapper;

    @Transactional
    public List<DoctorDto> getAllDoctors() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional
    public DoctorDto addDoctor(CreateDoctorDto dto) {
        log.info("Attempting to add doctor with email: {}", dto.email());
        repository.findByEmail(dto.email())
                .ifPresent(d -> { throw new DoctorAlreadyExistsException("Doctor with email " + dto.email() + " already exists");
        });
        Doctor doctor = mapper.toDoctor(dto);
        repository.save(doctor);
        return mapper.toDto(doctor);
    }

    @Transactional
    public DoctorDto assignToFacility(String email, Long facilityId) {
        log.info("Attempting to assign doctor {} to facility {}", email, facilityId);
        Doctor doctor = repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundDoctorException("Doctor with email " + email + " not found"));
        Facility facility = facilityRepository.findById(facilityId).orElseThrow(() -> new NotFoundFacilityException("Facility with id " + facilityId + " not found"));
        doctor.getFacilities().add(facility);
        return mapper.toDto(doctor);
    }
}
