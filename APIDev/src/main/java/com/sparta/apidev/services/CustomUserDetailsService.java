package com.sparta.apidev.services;

import com.sparta.apidev.entities.Trainee;
import com.sparta.apidev.entities.Trainer;
import com.sparta.apidev.repositories.TraineeRepository;
import com.sparta.apidev.repositories.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final TrainerRepository trainerRepository;
    private final TraineeRepository traineeRepository;

    @Autowired
    public CustomUserDetailsService(TrainerRepository trainerRepository,
                                    TraineeRepository traineeRepository) {
        this.trainerRepository = trainerRepository;
        this.traineeRepository = traineeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Check Trainer table first
        Optional<Trainer> trainerOpt = trainerRepository.findByTrainerName(username);
        if (trainerOpt.isPresent()) {
            Trainer trainer = trainerOpt.get();
            return User.withUsername(trainer.getTrainerName())
                    .password(trainer.getPassword())
                    .roles(trainer.getRole().name())
                    .build();
        }

        // Then check Trainee table
        Optional<Trainee> traineeOpt = traineeRepository.findByTraineeName(username);
        if (traineeOpt.isPresent()) {
            Trainee trainee = traineeOpt.get();
            return User.withUsername(trainee.getTraineeName())
                    .password(trainee.getPassword())
                    .roles(trainee.getRole().name())
                    .build();
        }

        throw new UsernameNotFoundException("User not found");
    }
}