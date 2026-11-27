package com.szalajdaniel.medicalclinic.mapper;

import com.szalajdaniel.medicalclinic.model.dto.CreateDoctorDto;
import com.szalajdaniel.medicalclinic.model.dto.DoctorDto;
import com.szalajdaniel.medicalclinic.model.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = FacilityMapper.class)
public interface DoctorMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "facilities", ignore = true)
    Doctor toDoctor(CreateDoctorDto dto);
    DoctorDto toDto(Doctor doctor);
}
