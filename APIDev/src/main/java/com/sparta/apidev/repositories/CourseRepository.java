package com.sparta.apidev.repositories;

import com.sparta.apidev.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,  Integer> {
    List<Course> findByCourseNameContainingIgnoreCase(String courseName);
}
