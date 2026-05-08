package com.sparta.apidev.services;

import com.sparta.apidev.repositories.TrainerRepository;
import com.sparta.apidev.entities.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

public class TrainerService {
    private final TrainerRepository trainerRepository;


    public TrainerService(TrainerRepository trainerRepository){
        if (trainerRepository == null) {
            throw new IllegalArgumentException("Trainer repository cannot be null");
        }
        this.trainerRepository = trainerRepository;
    }

    public List<Trainer> getAllTrainers(){return trainerRepository.findAll();}

    public Trainer getTrainerByID(int iD){
        return trainerRepository.findById(iD)
                .orElseThrow(() -> new NoSuchElementException("Trainer Not Found"));
    }

    public Trainer saveTrainer(Trainer trainer){
        if (trainer == null){
            throw new IllegalArgumentException("Trainer cannot be null");

        }
        return trainerRepository.save(trainer);
    }

    public boolean deleteTrainer(int iD){
        if (trainerRepository.existsById(iD)){
            trainerRepository.deleteById(iD);
            return true;
        }
        return false;
    }

    public Trainer updateTrainer(Trainer trainer){
        if (trainerRepository.existsById(trainer.getID())){
            return trainerRepository.save(trainer);
        }else {
            throw new IllegalArgumentException("Trainer with ID " + trainer.getID() + " does not exist.");
        }
    }


}
