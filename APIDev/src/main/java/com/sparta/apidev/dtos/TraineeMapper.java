package com.sparta.apidev.dtos;

import com.sparta.apidev.entities.Course;
import com.sparta.apidev.entities.Trainee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TraineeMapper {
    TraineeDTO toDTO(Trainee trainee);

    Trainee toEntity(TraineeDTO traineeDTO);
}
