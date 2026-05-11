package com.sparta.apidev.controllers;

import com.sparta.apidev.dtos.CourseDTO;
import com.sparta.apidev.services.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.sparta.apidev.dtos.CourseDTO;
import com.sparta.apidev.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // GET all courses
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {

        List<CourseDTO> courses = courseService.getAllCourses();

        return ResponseEntity.ok(courses);
    }

    // GET course by ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable int id) {

        CourseDTO course = courseService.getCourseById(id);

        if (course == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(course);
    }

    // CREATE new course
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {

        CourseDTO createdCourse = courseService.saveCourse(courseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    // UPDATE existing course
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(
            @PathVariable int id,
            @RequestBody CourseDTO courseDTO
    ) {

        CourseDTO updatedCourse = courseService.updateCourse(id, courseDTO);

        if (updatedCourse == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedCourse);
    }

    // DELETE course
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {

        boolean deleted = courseService.deleteCourse(id);

        if (deleted) {
            return ResponseEntity.ok("Course deleted successfully");
        }

        return ResponseEntity.notFound().build();
    }
}