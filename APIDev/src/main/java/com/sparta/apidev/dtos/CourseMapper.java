package com.sparta.apidev.dtos;

import com.sparta.apidev.entities.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "id", target = "courseId")
    CourseDTO toDTO(Course course);

    @Mapping(source = "courseId", target = "id")
    Course toEntity(CourseDTO courseDTO);
}