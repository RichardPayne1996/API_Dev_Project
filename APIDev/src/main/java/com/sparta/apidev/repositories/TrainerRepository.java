package com.sparta.apidev.repositories;

import com.sparta.apidev.entities.Trainee;
import com.sparta.apidev.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
    Optional<Trainer> findByTrainerName(String trainerName);
    List<Trainer> findByTrainerNameContainingIgnoreCase(String name);
}
