package com.sparta.apidev.services;

import com.sparta.apidev.dtos.TraineeDTO;
import com.sparta.apidev.dtos.TraineeMapper;
import com.sparta.apidev.entities.Trainee;
import com.sparta.apidev.repositories.TraineeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class TraineeServiceTest {

    private final TraineeRepository mockRepository =
            Mockito.mock(TraineeRepository.class);

    private final TraineeMapper mockMapper =
            Mockito.mock(TraineeMapper.class);

    private final TraineeService sut =
            new TraineeService(mockRepository, mockMapper);

    @Test
    @DisplayName("Construct Service")
    void constructServiceTest() {

        Assertions.assertInstanceOf(TraineeService.class, sut);
    }

    @Test
    @DisplayName("Get All Trainees")
    void getAllTraineesTest() {

        // Arrange
        Trainee trainee1 = new Trainee();
        trainee1.setTraineeId(1);

        Trainee trainee2 = new Trainee();
        trainee2.setTraineeId(2);

        List<Trainee> trainees = List.of(trainee1, trainee2);

        TraineeDTO dto1 = new TraineeDTO();
        dto1.setTraineeId(1);

        TraineeDTO dto2 = new TraineeDTO();
        dto2.setTraineeId(2);

        Mockito.when(mockRepository.findAll())
                .thenReturn(trainees);

        Mockito.when(mockMapper.toDTO(trainee1))
                .thenReturn(dto1);

        Mockito.when(mockMapper.toDTO(trainee2))
                .thenReturn(dto2);

        // Act
        List<TraineeDTO> result = sut.getAllTrainees();

        // Assert
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(1, result.get(0).getTraineeId());
        Assertions.assertEquals(2, result.get(1).getTraineeId());
    }

    @Test
    @DisplayName("Get Trainee By ID - Happy Path")
    void getTraineeByIdHappyPathTest() {

        // Arrange
        Trainee trainee = new Trainee();
        trainee.setTraineeId(1);

        TraineeDTO dto = new TraineeDTO();
        dto.setTraineeId(1);

        Mockito.when(mockRepository.findById(1))
                .thenReturn(Optional.of(trainee));

        Mockito.when(mockMapper.toDTO(trainee))
                .thenReturn(dto);

        // Act
        TraineeDTO result = sut.getTraineeById(1);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTraineeId());
    }

    @Test
    @DisplayName("Get Trainee By ID - Not Found")
    void getTraineeByIdUnhappyPathTest() {

        // Arrange
        Mockito.when(mockRepository.findById(1))
                .thenReturn(Optional.empty());

        // Act
        TraineeDTO result = sut.getTraineeById(1);

        // Assert
        Assertions.assertNull(result);
    }

    @Test
    @DisplayName("Save Trainee")
    void saveTraineeTest() {

        // Arrange
        Trainee trainee = new Trainee();
        trainee.setTraineeId(1);

        TraineeDTO dto = new TraineeDTO();
        dto.setTraineeId(1);

        Mockito.when(mockRepository.save(trainee))
                .thenReturn(trainee);

        Mockito.when(mockMapper.toDTO(trainee))
                .thenReturn(dto);

        // Act
        TraineeDTO result = sut.saveTrainee(trainee);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTraineeId());
    }

    @Test
    @DisplayName("Delete Trainee - Success")
    void deleteTraineeSuccessTest() {

        // Arrange
        Mockito.when(mockRepository.existsById(1))
                .thenReturn(true);

        // Act
        boolean result = sut.deleteTrainee(1);

        // Assert
        Assertions.assertTrue(result);

        Mockito.verify(mockRepository, Mockito.times(1))
                .deleteById(1);
    }

    @Test
    @DisplayName("Delete Trainee - Not Found")
    void deleteTraineeFailTest() {

        // Arrange
        Mockito.when(mockRepository.existsById(1))
                .thenReturn(false);

        // Act
        boolean result = sut.deleteTrainee(1);

        // Assert
        Assertions.assertFalse(result);

        Mockito.verify(mockRepository, Mockito.never())
                .deleteById(1);
    }

    @Test
    @DisplayName("Update Trainee - Success")
    void updateTraineeSuccessTest() {

        // Arrange
        TraineeDTO dto = new TraineeDTO();
        dto.setTraineeId(1);
        dto.setTraineeName("John");

        Trainee trainee = new Trainee();
        trainee.setTraineeId(1);

        Mockito.when(mockRepository.findById(1))
                .thenReturn(Optional.of(trainee));

        Mockito.when(mockRepository.save(trainee))
                .thenReturn(trainee);

        Mockito.when(mockMapper.toDTO(trainee))
                .thenReturn(dto);

        // Act
        TraineeDTO result = sut.updateTrainee(1, dto);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals("John", result.getTraineeName());
    }

    @Test
    @DisplayName("Update Trainee - Not Found")
    void updateTraineeFailTest() {

        // Arrange
        TraineeDTO dto = new TraineeDTO();
        dto.setTraineeId(1);

        Mockito.when(mockRepository.findById(1))
                .thenReturn(Optional.empty());

        // Act + Assert
        Assertions.assertThrows(
                RuntimeException.class,
                () -> sut.updateTrainee(1, dto)
        );
    }
}