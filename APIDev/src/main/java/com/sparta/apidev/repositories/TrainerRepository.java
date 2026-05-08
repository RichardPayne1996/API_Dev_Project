package com.sparta.apidev.repositories;

import com.sparta.apidev.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
    //public List<Trainer> findAllByTrainerID();
}
