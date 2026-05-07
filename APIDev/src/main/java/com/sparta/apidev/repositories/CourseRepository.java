package com.sparta.apidev.repositories;

import com.sparta.apidev.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,  Integer> {
}
