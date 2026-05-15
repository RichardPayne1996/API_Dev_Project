package com.sparta.apidev.controllers;

import com.sparta.apidev.dtos.TraineeDTO;
import com.sparta.apidev.dtos.TrainerDTO;
import com.sparta.apidev.dtos.TrainerMapper;
import com.sparta.apidev.entities.Trainer;
import com.sparta.apidev.repositories.TrainerRepository;
import com.sparta.apidev.services.TrainerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    private final TrainerService trainerService;
    private final TrainerMapper trainerMapper;
    private final TrainerRepository trainerRepository;

    public TrainerController(TrainerService trainerService, TrainerMapper trainerMapper, TrainerRepository trainerRepository) {
        this.trainerService = trainerService;
        this.trainerMapper = trainerMapper;
        this.trainerRepository = trainerRepository;
    }

    @Operation(summary = "Get all teachers", description = "Provides a list of all teachers")
    @GetMapping(value = "/")
    public ResponseEntity<List<TrainerDTO>> getAllTrainers() {
        var trainers = trainerService.getAllTrainers();
        return ResponseEntity.ok().body(trainers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerDTO> getTrainer(@PathVariable int id) {
        var trainer = trainerService.getTrainerByID(id);
        if (trainer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(trainer);
    }

    // create trainer
    @PostMapping
    public ResponseEntity<TrainerDTO> createTrainer(@RequestBody TrainerDTO trainerDTO) {
        if (trainerDTO.getTrainerName() == null || trainerDTO.getTrainerEmail() == null || trainerDTO.getTrainerDob() == null || trainerDTO.getTrainerTitle() == null) {
            String exception = "Incomplete trainer information, missing the following data:";
            if (trainerDTO.getTrainerName() == null) {
                exception += " Trainer Name,";
            }
            if (trainerDTO.getTrainerEmail() == null) {
                exception += " Trainer Email,";
            }
            if (trainerDTO.getTrainerDob() == null) {
                exception += " Trainer DOB,";
            }
            if (trainerDTO.getTrainerTitle() == null) {
                exception += " Trainer Title,";
            }
            throw new RuntimeException(exception.replace(",$", "."));
        }
        Trainer trainer = trainerMapper.toEntity(trainerDTO);
        Trainer saved = trainerRepository.save(trainer);
        return ResponseEntity.ok(trainerMapper.toDTO(saved));
    }
    // update trainer
    @PatchMapping("/{id}")
    public ResponseEntity<TrainerDTO> updateCustomer(@PathVariable int id, @RequestBody TrainerDTO trainer) {
        trainer.setTrainerId(id);
        TrainerDTO oldTrainee = trainerService.getTrainerByID(id);
        if (oldTrainee == null) {
            return ResponseEntity.notFound().build();
        }
        if (trainer.getTrainerTitle() == null ||  trainer.getTrainerTitle().equals("string")) {
            trainer.setTrainerTitle(oldTrainee.getTrainerTitle());
        }
        if (trainer.getTrainerEmail() == null || trainer.getTrainerEmail().equals("string")) {
            trainer.setTrainerEmail(oldTrainee.getTrainerEmail());
        }
        if (trainer.getTrainerDob() == null || trainer.getTrainerDob().equals(LocalDate.now())) {
            trainer.setTrainerDob(oldTrainee.getTrainerDob());
        }
        if (trainer.getTrainerName() == null || trainer.getTrainerName().equals("string")) {
            trainer.setTrainerName(oldTrainee.getTrainerName());
        }
        TrainerDTO updatedTrainer = trainerService.updateTrainer(id, trainer);
        return ResponseEntity.ok().body(updatedTrainer);
    }
    // delete trainer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable int id) {
        boolean deleteTrainer = trainerService.deleteTrainer(id);
        if (deleteTrainer) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
