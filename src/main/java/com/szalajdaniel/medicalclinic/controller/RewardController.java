package com.szalajdaniel.medicalclinic.controller;

import com.szalajdaniel.medicalclinic.model.dto.CreateRewardCommandDto;
import com.szalajdaniel.medicalclinic.model.dto.RewardDto;
import com.szalajdaniel.medicalclinic.model.dto.UpdateRewardCommandDto;
import com.szalajdaniel.medicalclinic.service.RewardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RewardController {
    private final RewardService rewardService;

    @GetMapping("/programs/{programId}/rewards")
    public List<RewardDto> getRewardsByProgramId(@PathVariable Long programId) {
        return rewardService.getRewardsByProgramId(programId);
    }

    @PostMapping("/programs/{programId}/rewards")
    @ResponseStatus(HttpStatus.CREATED)
    public RewardDto addReward(@PathVariable Long programId, @Valid @RequestBody CreateRewardCommandDto createRewardCommandDto) {
        return rewardService.addReward(programId, createRewardCommandDto);
    }

    @GetMapping("/rewards/{rewardId}")
    public RewardDto getRewardById(@PathVariable Long rewardId) {
        return rewardService.getRewardById(rewardId);
    }

    @PutMapping("/rewards/{rewardId}")
    public RewardDto updateReward(@PathVariable Long rewardId, @Valid @RequestBody UpdateRewardCommandDto updateRewardCommandDto) {
        return rewardService.updateReward(rewardId, updateRewardCommandDto);
    }

    @DeleteMapping("/rewards/{rewardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReward(@PathVariable Long rewardId) {
        rewardService.deleteReward(rewardId);
    }
}