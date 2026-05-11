package com.sparta.apidev.repositories;

import com.sparta.apidev.entities.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraineeRepository extends JpaRepository<Trainee, Integer> {
}
