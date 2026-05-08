package com.sparta.apidev.services;


import com.sparta.apidev.dtos.CourseDTO;
import com.sparta.apidev.dtos.CourseMapper;
import com.sparta.apidev.entities.Course;
import com.sparta.apidev.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO getCourseById(int id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDTO)
                .orElse(null);
    }

    public CourseDTO saveCourse(CourseDTO dto) {
        Course course = courseMapper.toEntity(dto);
        Course saved = courseRepository.save(course);
        return courseMapper.toDTO(saved);
    }

    public boolean deleteCourse(int id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public CourseDTO updateCourse(int id, CourseDTO dto) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setCourseName(dto.getCourseName());
        course.setDescription(dto.getDescription());

        Course updated = courseRepository.save(course);

        return courseMapper.toDTO(updated);
    }
}