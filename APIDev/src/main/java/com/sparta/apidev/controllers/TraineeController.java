package com.sparta.apidev.controllers;

import com.sparta.apidev.dtos.TraineeDTO;
import com.sparta.apidev.dtos.TraineeMapper;
import com.sparta.apidev.entities.Trainee;
import com.sparta.apidev.repositories.TraineeRepository;
import com.sparta.apidev.services.TraineeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/trainees")
public class TraineeController {

    private final TraineeService traineeService;
    private final TraineeMapper traineeMapper;
    private final TraineeRepository traineeRepository;

    public TraineeController(TraineeService traineeService, TraineeMapper traineeMapper, TraineeRepository traineeRepository) {
        this.traineeService = traineeService;
        this.traineeMapper = traineeMapper;
        this.traineeRepository = traineeRepository;
    }

    @Operation(summary = "Get all students", description = "Provides a list of all trainees")
    @GetMapping(value = "/")
    public ResponseEntity<List<TraineeDTO>> getAllTrainees() {
        var trainees = traineeService.getAllTrainees();
        return ResponseEntity.ok().body(trainees);
    }

    @Operation(summary = "Get students by Id", description = "If a correct id is entered, returns a trainee")
    @GetMapping("/{id}")
    public ResponseEntity<TraineeDTO> getTraineeById(@RequestParam(name = "id") int id) {
        var trainee = traineeService.getTraineeById(id);
        if (trainee != null) {
            return ResponseEntity.ok().body(trainee);
        }
        throw new RuntimeException("Trainee with id " + id + " not found");
    }

    @Operation(summary = "Add a new student", description = "If a student has all the correct information, adds them")
    @PostMapping
    public ResponseEntity<TraineeDTO> createTrainee(@RequestBody TraineeDTO traineeDTO) {
        if (traineeDTO.getTraineeName() == null || traineeDTO.getTraineeEmail() == null || traineeDTO.getTraineeDob() == null || traineeDTO.getTraineeTitle() == null) {
            String exception = "Incomplete trainee information, missing the following data:";
            if (traineeDTO.getTraineeName() == null) {
                exception += " Trainee Name,";
            }
            if (traineeDTO.getTraineeEmail() == null) {
                exception += " Trainee Email,";
            }
            if (traineeDTO.getTraineeDob() == null) {
                exception += " Trainee DOB,";
            }
            if (traineeDTO.getTraineeTitle() == null) {
                exception += " Trainee Title,";
            }
            throw new RuntimeException(exception.replaceAll(",$", "."));
        }
        Trainee trainee = traineeMapper.toEntity(traineeDTO);
        Trainee saved = traineeRepository.save(trainee);
        return ResponseEntity.ok(traineeMapper.toDTO(saved));
    }

    @Operation(summary = "Update a student", description = "updates a student with new information")
    @PatchMapping("/{id}")
    public ResponseEntity<TraineeDTO> updateCustomer(@PathVariable int id, @RequestBody TraineeDTO trainee) {
        trainee.setTraineeId(id);
        TraineeDTO oldTrainee = traineeService.getTraineeById(id);
        if (oldTrainee == null) {
            return ResponseEntity.notFound().build();
        }
        if (trainee.getTraineeTitle() == null ||  trainee.getTraineeTitle().equals("string")) {
            trainee.setTraineeTitle(oldTrainee.getTraineeTitle());
        }
        if (trainee.getTraineeEmail() == null || trainee.getTraineeEmail().equals("string")) {
            trainee.setTraineeEmail(oldTrainee.getTraineeEmail());
        }
        if (trainee.getTraineeDob() == null || trainee.getTraineeDob().equals(LocalDate.now())) {
            trainee.setTraineeDob(oldTrainee.getTraineeDob());
        }
        if (trainee.getTraineeName() == null || trainee.getTraineeName().equals("string")) {
            trainee.setTraineeName(oldTrainee.getTraineeName());
        }
        TraineeDTO updatedTrainee = traineeService.updateTrainee(id, trainee);
        return ResponseEntity.ok().body(updatedTrainee);
    }

    @Operation(summary = "Delete a student", description = "Deletes a student from the database")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainee(@PathVariable int id) {
        //Comment to see if this lets me push again
        boolean isDeleted = traineeService.deleteTrainee(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}