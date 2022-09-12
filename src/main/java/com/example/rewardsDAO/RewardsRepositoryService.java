package com.example.rewardsDAO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rewardsService.Rewards;

@Repository
public interface RewardsRepositoryService extends JpaRepository<Rewards, Long> {

}
