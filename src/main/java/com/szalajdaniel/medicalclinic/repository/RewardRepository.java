package com.szalajdaniel.medicalclinic.repository;

import com.szalajdaniel.medicalclinic.model.entity.Reward;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class RewardRepository {

    private final List<Reward> rewards = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Reward> getRewards() {
        return new ArrayList<>(rewards);
    }

    public List<Reward> findByProgramId(Long programId) {
        return rewards.stream()
                .filter(reward -> reward.getProgramId().equals(programId))
                .toList();
    }

    public Optional<Reward> findById(Long rewardId) {
        return rewards.stream()
                .filter(reward -> reward.getId().equals(rewardId))
                .findFirst();
    }

    public void addReward(Reward reward) {
        reward.setId(idGenerator.getAndIncrement());
        rewards.add(reward);
    }

    public void deleteReward(Long rewardId) {
        rewards.removeIf(reward -> reward.getId().equals(rewardId));
    }
}
