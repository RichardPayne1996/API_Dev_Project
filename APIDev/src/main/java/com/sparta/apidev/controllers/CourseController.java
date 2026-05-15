package com.sparta.apidev.controllers;

import com.sparta.apidev.dtos.CourseDTO;
import com.sparta.apidev.services.CourseService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@Tag(
        name = "Course Management",
        description = "Endpoints for managing Sparta Academy courses, including creating, retrieving, updating, and deleting course records."
)
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(
            summary = "Retrieve all courses",
            description = "Returns a complete list of all available courses in the system."
    )
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {

        List<CourseDTO> courses = courseService.getAllCourses();

        return ResponseEntity.ok(courses);
    }

    @Operation(
            summary = "Retrieve a course by ID",
            description = "Returns a single course matching the provided course ID."
    )
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable int id) {

        CourseDTO course = courseService.getCourseById(id);

        if (course == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(course);
    }

    @Operation(
            summary = "Create a new course",
            description = "Creates and stores a new course record in the database."
    )
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {

        CourseDTO createdCourse = courseService.saveCourse(courseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    @Operation(
            summary = "Update an existing course",
            description = "Updates the details of an existing course using the provided course ID."
    )
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

    @Operation(
            summary = "Delete a course",
            description = "Deletes the course associated with the provided course ID."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {

        boolean deleted = courseService.deleteCourse(id);

        if (deleted) {
            return ResponseEntity.ok("Course deleted successfully");
        }

        return ResponseEntity.notFound().build();
    }
}