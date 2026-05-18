package com.sparta.apidev.dtos;

import com.sparta.apidev.entities.Course;
import com.sparta.apidev.entities.Trainer;
import com.sparta.apidev.enums.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrainerMapper {
    TrainerDTO toDTO(Trainer trainer);

    Trainer toEntity(TrainerDTO trainerDTO);

    default String map(Role role) {
        return role != null ? role.name() : null;
    }

    // String -> Enum
    default Role map(String role) {
        return role != null ? Role.valueOf(role.toUpperCase()) : null;
    }
}
