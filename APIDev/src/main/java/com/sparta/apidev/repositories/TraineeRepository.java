package com.sparta.apidev.repositories;

import com.sparta.apidev.entities.Trainee;
import com.sparta.apidev.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TraineeRepository extends JpaRepository<Trainee, Integer> {
    Optional<Trainee> findByTraineeName(String traineeName);
    List<Trainee> findByTraineeNameContainingIgnoreCase(String traineeName);


}
