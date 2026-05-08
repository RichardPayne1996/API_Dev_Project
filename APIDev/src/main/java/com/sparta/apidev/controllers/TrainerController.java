package com.sparta.apidev.controllers;

import com.sparta.apidev.dtos.TrainerDTO;
import com.sparta.apidev.entities.Trainer;
import com.sparta.apidev.services.TrainerService;
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

    public ResponseEntity<List<TrainerDTO>> getAllTrainers() {
        return ResponseEntity.ok(trainerService.getAllTrainers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerDTO> getTrainer(@PathVariable Integer id) {
        return ResponseEntity.ok(
                trainerService.getTrainerByID(id)
        );
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
