package com.sparta.apidev.controllers;

import com.sparta.apidev.dtos.TrainerDTO;
import com.sparta.apidev.dtos.TrainerMapper;
import com.sparta.apidev.entities.Trainer;
import com.sparta.apidev.repositories.TrainerRepository;
import com.sparta.apidev.services.TrainerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainers")
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
    public ResponseEntity<TrainerDTO> getTrainer(@PathVariable Integer id) {
        return ResponseEntity.ok(
                trainerService.getTrainerByID(id)
        );
    }

    // create trainer
    @PostMapping
    public ResponseEntity<TrainerDTO> createTrainer(@RequestBody TrainerDTO trainerDTO) {
        if (trainerService.getTrainerByID(trainerDTO.getTrainerId()) != null) {
            throw new RuntimeException("Trainer with id " + trainerDTO.getTrainerId() + " already exists");
        }
        if (trainerDTO.getTrainerName() == null || trainerDTO.getTrainerEmail() == null || trainerDTO.getTrainerDOB() == null || trainerDTO.getTrainerTitle() == null) {
            String exception = "Incomplete trainer information, missing the following data:";
            if (trainerDTO.getTrainerName() == null) {
                exception += " Trainer Name,";
            }
            if (trainerDTO.getTrainerEmail() == null) {
                exception += " Trainer Email,";
            }
            if (trainerDTO.getTrainerDOB() == null) {
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
    @PutMapping("/{id}")
    public ResponseEntity<TrainerDTO> updateTrainer(
            @PathVariable Integer id,
            @RequestBody TrainerDTO trainerDTO) {

        TrainerDTO updateTrainer = trainerService.updateTrainer(id, trainerDTO);
        return ResponseEntity.ok(updateTrainer);
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
