package com.sparta.apidev.dtos;

import com.sparta.apidev.entities.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDTO toDTO(Course course);

    Course toEntity(CourseDTO courseDTO);
}