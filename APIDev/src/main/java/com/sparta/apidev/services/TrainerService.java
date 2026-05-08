package com.sparta.apidev.services;

import com.sparta.apidev.dtos.TrainerDTO;
import com.sparta.apidev.dtos.TrainerMapper;
import com.sparta.apidev.repositories.TrainerRepository;
import com.sparta.apidev.entities.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final TrainerMapper trainerMapper;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository, TrainerMapper trainerMapper){
        if (trainerRepository == null || trainerMapper == null) {
            throw new IllegalArgumentException("Trainer repository cannot be null");
        }
        this.trainerRepository = trainerRepository;
        this.trainerMapper = trainerMapper;
    }

    public List<TrainerDTO> getAllTrainers(){
        return trainerRepository.findAll().stream().map(trainerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TrainerDTO getTrainerByID(int iD){
        return trainerRepository.findById(iD).map(trainerMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("Trainer Not Found"));
    }

    public TrainerDTO saveTrainer(Trainer trainer){
        if (trainer == null){
            throw new IllegalArgumentException("Trainer cannot be null");

        }
        return trainerMapper.toDTO(trainerRepository.save(trainer));
    }

    public boolean deleteTrainer(int iD){
        if (trainerRepository.existsById(iD)){
            trainerRepository.deleteById(iD);
            return true;
        }
        return false;
    }

    public TrainerDTO updateTrainer(Trainer trainer){
        if (trainerRepository.existsById(trainer.getID())){
            return trainerMapper.toDTO(trainerRepository.save(trainer));
        }else {
            throw new IllegalArgumentException("Trainer with ID " + trainer.getID() + " does not exist.");
        }
    }


}
