package com.sparta.apidev.dtos;

import com.sparta.apidev.entities.Course;
import com.sparta.apidev.entities.Trainer;
import com.sparta.apidev.enums.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TrainerMapper {

    @Mapping(target = "courses", source = "teacherCourse")
    TrainerDTO toDTO(Trainer trainer);

    Trainer toEntity(TrainerDTO trainerDTO);

    default String map(Role role) {
        return role != null ? role.name() : null;
    }

    // String -> Enum
    default Role map(String role) {
        return role != null ? Role.valueOf(role.toUpperCase()) : null;
    }

    default Set<CourseDTO> mapCourses(Set<Course> courses) {
        if (courses == null) return null;
        return courses.stream()
                .map(c -> new CourseDTO(c.getId(), c.getCourseName(), c.getDescription()))
                .collect(Collectors.toSet());
    }
}
