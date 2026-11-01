package com.szalajdaniel.medicalclinic.mapper;

import com.szalajdaniel.medicalclinic.model.dto.CreateRewardCommandDto;
import com.szalajdaniel.medicalclinic.model.dto.RewardDto;
import com.szalajdaniel.medicalclinic.model.dto.UpdateRewardCommandDto;
import com.szalajdaniel.medicalclinic.model.entity.Reward;

public class RewardMapper {

    public static Reward toReward(CreateRewardCommandDto command, Long programId) {
        Reward reward = new Reward();
        reward.setName(command.name());
        reward.setDescription(command.description());
        reward.setPointsCost(command.pointsCost());
        reward.setProgramId(programId);
        reward.setStartDate(command.startDate());
        reward.setEndDate(command.endDate());
        reward.setActive(command.active());
        reward.setAvailableQuantity(command.availableQuantity());
        return reward;
    }

    public static RewardDto toDto(Reward reward, boolean available) {
        return new RewardDto(
                reward.getId(),
                reward.getName(),
                reward.getDescription(),
                reward.getPointsCost(),
                reward.getProgramId(),
                reward.getStartDate(),
                reward.getEndDate(),
                reward.isActive(),
                reward.getAvailableQuantity(),
                available
        );
    }

    public static void updateReward(
            Reward reward,
            UpdateRewardCommandDto command) {
        reward.setName(command.name());
        reward.setDescription(command.description());
        reward.setPointsCost(command.pointsCost());
        reward.setStartDate(command.startDate());
        reward.setEndDate(command.endDate());
        reward.setActive(command.active());
        reward.setAvailableQuantity(command.availableQuantity());
    }
}
