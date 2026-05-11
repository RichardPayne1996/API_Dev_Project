package com.sparta.apidev.controllers;

import com.sparta.apidev.dtos.TraineeDTO;
import com.sparta.apidev.dtos.TrainerDTO;
import com.sparta.apidev.entities.Trainer;
import com.sparta.apidev.services.TrainerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

    private TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<TrainerDTO> getTrainer(@PathVariable Integer id) {
        return ResponseEntity.ok(
                trainerService.getTrainerByID(id)
        );
    }

    @Operation(summary = "Get all teachers", description = "Provides a list of all teachers")
    @GetMapping(value = "/")
    public ResponseEntity<List<TrainerDTO>> getAllTrainers() {
        var trainers = trainerService.getAllTrainers();
        return ResponseEntity.ok().body(trainers);
    }
    // create trainer
    @PostMapping
    public ResponseEntity<TrainerDTO> createTrainer(@RequestBody Trainer trainer) {
        TrainerDTO savedTrainer = trainerService.saveTrainer(trainer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTrainer);
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
