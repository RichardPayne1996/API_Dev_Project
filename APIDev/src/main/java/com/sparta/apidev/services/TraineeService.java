package com.sparta.apidev.services;

import com.sparta.apidev.dtos.TraineeDTO;
import com.sparta.apidev.dtos.TraineeMapper;
import com.sparta.apidev.entities.Trainee;
import com.sparta.apidev.repositories.TraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TraineeService {

    private final TraineeRepository traineeRepository;
    private final TraineeMapper traineeMapper;

    @Autowired
    public TraineeService(TraineeRepository traineeRepository, TraineeMapper traineeMapper) {
        if (traineeRepository == null || traineeMapper == null) {
            throw new  IllegalArgumentException();
        }
        this.traineeRepository = traineeRepository;
        this.traineeMapper = traineeMapper;
    }

    public List<TraineeDTO> getAllTrainees() {
        return traineeRepository.findAll().stream()
                .map(traineeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TraineeDTO getTraineeById(Integer id) {
        return traineeRepository.findById(id).map(traineeMapper::toDTO)
                .orElse(null);
    }
    public TraineeDTO saveTrainee(TraineeDTO dto) {

        Trainee trainee = traineeMapper.toEntity(dto);

        Trainee saved = traineeRepository.save(trainee);

        return traineeMapper.toDTO(saved);
    }

    public TraineeDTO saveTrainee(Trainee trainee) {
        return traineeMapper.toDTO(traineeRepository.save(trainee));
    }

    public boolean deleteTrainee(Integer id) {
        if (traineeRepository.existsById(id)) {
            traineeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public TraineeDTO updateTrainee(int id, TraineeDTO dto) {

        Trainee trainee = traineeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trainee not found"));

        trainee.setTraineeName(dto.getTraineeName());
        trainee.setTraineeDob(dto.getTraineeDob());
        trainee.setTraineeEmail(dto.getTraineeEmail());
        trainee.setTraineeTitle(dto.getTraineeTitle());

        Trainee updated = traineeRepository.save(trainee);

        return traineeMapper.toDTO(updated);
    }
    public TraineeDTO getTraineeByName(String name) {

        Trainee trainee = traineeRepository.findByTraineeName(name)
                .orElseThrow(() -> new RuntimeException("Trainee not found: " + name));

        return traineeMapper.toDTO(trainee);
    }

}
