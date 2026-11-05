package com.szalajdaniel.medicalclinic.service;

import com.szalajdaniel.medicalclinic.mapper.RewardMapper;
import com.szalajdaniel.medicalclinic.model.dto.CreateRewardCommandDto;
import com.szalajdaniel.medicalclinic.model.dto.RewardDto;
import com.szalajdaniel.medicalclinic.model.dto.UpdateRewardCommandDto;
import com.szalajdaniel.medicalclinic.model.entity.Reward;
import com.szalajdaniel.medicalclinic.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RewardService {
    private final RewardRepository rewardRepository;
    private final RewardMapper rewardMapper;

    public List<RewardDto> getRewardsByProgramId(Long programId) {
        return rewardRepository.findByProgramId(programId)
                .stream()
                .map(reward -> rewardMapper.toDto(reward, isAvailable(reward)))
                .toList();
    }

    public RewardDto addReward(Long programId, CreateRewardCommandDto command) {
        Reward reward = rewardMapper.toReward(command, programId);
        rewardRepository.addReward(reward);
        return rewardMapper.toDto(reward, isAvailable(reward)
        );
    }

    public RewardDto getRewardById(Long rewardId) {
        Reward reward = findReward(rewardId);
        return rewardMapper.toDto(reward, isAvailable(reward)
        );
    }

    public RewardDto updateReward(Long rewardId, UpdateRewardCommandDto command) {
        Reward reward = findReward(rewardId);
        rewardMapper.updateReward(reward, command);
        return rewardMapper.toDto(reward, isAvailable(reward)
        );
    }

    public void deleteReward(Long rewardId) {
        findReward(rewardId);
        rewardRepository.deleteReward(rewardId);
    }

    private Reward findReward(Long rewardId) {
        return rewardRepository.findById(rewardId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "No reward found with the given id"));
    }

    private boolean isAvailable(Reward reward) {
        LocalDate today = LocalDate.now();
        boolean active = reward.isActive();
        boolean afterStart = reward.getStartDate() == null
                || !today.isBefore(reward.getStartDate());
        boolean beforeEnd = reward.getEndDate() == null
                || !today.isAfter(reward.getEndDate());
        boolean quantityAvailable = reward.getAvailableQuantity() == null
                || reward.getAvailableQuantity() > 0;
        return active && afterStart && beforeEnd && quantityAvailable;
    }
}