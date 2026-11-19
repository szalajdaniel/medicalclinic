package com.szalajdaniel.medicalclinic.mapper;

import com.szalajdaniel.medicalclinic.model.dto.CreateFacilityDto;
import com.szalajdaniel.medicalclinic.model.dto.FacilityDto;
import com.szalajdaniel.medicalclinic.model.entity.Facility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FacilityMapper {
    @Mapping(target = "facilityId", ignore = true)
    Facility toFacility(CreateFacilityDto dto);
    FacilityDto toDto(Facility facility);
}
