package com.sparta.apidev.services;

import com.sparta.apidev.dtos.TrainerDTO;
import com.sparta.apidev.dtos.TrainerMapper;
import com.sparta.apidev.entities.Trainer;
import com.sparta.apidev.repositories.TrainerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

class TrainerServiceTest {

    private final TrainerRepository mockRepository =
            Mockito.mock(TrainerRepository.class);

    private final TrainerMapper mockMapper =
            Mockito.mock(TrainerMapper.class);

    private final TrainerService sut =
            new TrainerService(mockRepository, mockMapper);

    @Test
    @DisplayName("Construct Trainer Service")
    void constructServiceTest() {

        Assertions.assertInstanceOf(TrainerService.class, sut);
    }

    @Test
    @DisplayName("Get All Trainers")
    void getAllTrainersTest() {

        // Arrange
        Trainer trainer1 = new Trainer();
        trainer1.setID(1);

        Trainer trainer2 = new Trainer();
        trainer2.setID(2);

        List<Trainer> trainers = List.of(trainer1, trainer2);

        TrainerDTO dto1 = new TrainerDTO();
        dto1.setTrainerId(1);

        TrainerDTO dto2 = new TrainerDTO();
        dto2.setTrainerId(2);

        Mockito.when(mockRepository.findAll())
                .thenReturn(trainers);

        Mockito.when(mockMapper.toDTO(trainer1))
                .thenReturn(dto1);

        Mockito.when(mockMapper.toDTO(trainer2))
                .thenReturn(dto2);

        // Act
        List<TrainerDTO> result = sut.getAllTrainers();

        // Assert
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(1, result.get(0).getTrainerId());
        Assertions.assertEquals(2, result.get(1).getTrainerId());
    }

    @Test
    @DisplayName("Get Trainer By ID - Success")
    void getTrainerByIdSuccessTest() {

        // Arrange
        Trainer trainer = new Trainer();
        trainer.setID(1);

        TrainerDTO dto = new TrainerDTO();
        dto.setTrainerId(1);

        Mockito.when(mockRepository.findById(1))
                .thenReturn(Optional.of(trainer));

        Mockito.when(mockMapper.toDTO(trainer))
                .thenReturn(dto);

        // Act
        TrainerDTO result = sut.getTrainerByID(1);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTrainerId());
    }


    @Test
    @DisplayName("Get Trainer By ID - Not Found")
    void getTrainerByIdFailureTest() {

        // Arrange
        Mockito.when(mockRepository.findById(1))
                .thenReturn(Optional.empty());

        // Act + Assert
        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> sut.getTrainerByID(1)
        );
    }

    @Test
    @DisplayName("Save Trainer - Success")
    void saveTrainerSuccessTest() {

        // Arrange
        Trainer trainer = new Trainer();
        trainer.setID(1);

        TrainerDTO dto = new TrainerDTO();
        dto.setTrainerId(1);

        Mockito.when(mockRepository.save(trainer))
                .thenReturn(trainer);

        Mockito.when(mockMapper.toDTO(trainer))
                .thenReturn(dto);

        // Act
        TrainerDTO result = sut.saveTrainer(trainer);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTrainerId());
    }

    @Test
    @DisplayName("Save Trainer - Null Input")
    void saveTrainerNullTest() {

        // Act + Assert
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> sut.saveTrainer(null)
        );
    }

    @Test
    @DisplayName("Delete Trainer - Success")
    void deleteTrainerSuccessTest() {

        // Arrange
        Mockito.when(mockRepository.existsById(1))
                .thenReturn(true);

        // Act
        boolean result = sut.deleteTrainer(1);

        // Assert
        Assertions.assertTrue(result);

        Mockito.verify(mockRepository, Mockito.times(1))
                .deleteById(1);
    }

    @Test
    @DisplayName("Delete Trainer - Not Found")
    void deleteTrainerFailureTest() {

        // Arrange
        Mockito.when(mockRepository.existsById(1))
                .thenReturn(false);

        // Act
        boolean result = sut.deleteTrainer(1);

        // Assert
        Assertions.assertFalse(result);

        Mockito.verify(mockRepository, Mockito.never())
                .deleteById(1);
    }

    @Test
    @DisplayName("Update Trainer - Success")
    void updateTrainerSuccessTest() {

        // Arrange
        Trainer trainer = new Trainer();
        trainer.setID(1);

        TrainerDTO dto = new TrainerDTO();
        dto.setTrainerId(1);

        Mockito.when(mockRepository.existsById(1))
                .thenReturn(true);

        Mockito.when(mockRepository.findById(1)).thenReturn(Optional.of(trainer));

        Mockito.when(mockRepository.save(trainer))
                .thenReturn(trainer);

        Mockito.when(mockMapper.toDTO(trainer))
                .thenReturn(dto);

        // Act
        TrainerDTO result = sut.updateTrainer(1, dto);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTrainerId());
    }

    @Test
    @DisplayName("Update Trainer - Not Found")
    void updateTrainerFailureTest() {

        // Arrange
        TrainerDTO trainer = new TrainerDTO();
        trainer.setTrainerId(1);

        Mockito.when(mockRepository.existsById(1))
                .thenReturn(false);

        // Act + Assert
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> sut.updateTrainer(1,trainer)
        );
    }
}
