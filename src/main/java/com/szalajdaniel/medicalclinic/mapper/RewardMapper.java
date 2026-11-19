package com.szalajdaniel.medicalclinic.mapper;

import com.szalajdaniel.medicalclinic.model.dto.CreateRewardCommandDto;
import com.szalajdaniel.medicalclinic.model.dto.RewardDto;
import com.szalajdaniel.medicalclinic.model.dto.UpdateRewardCommandDto;
import com.szalajdaniel.medicalclinic.model.entity.Reward;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RewardMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "programId", source = "programId")
    Reward toReward(CreateRewardCommandDto command, Long programId);

    @Mapping(target = "available", source = "available")
    RewardDto toDto(Reward reward, boolean available);
    void updateReward(@MappingTarget Reward reward, UpdateRewardCommandDto command);
}