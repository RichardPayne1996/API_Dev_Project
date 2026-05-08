package com.sparta.apidev.dtos;

public class CourseDTO {
    private Integer id;
    private String courseName;
    private String description;

    public CourseDTO() {}

    public CourseDTO(Integer id, String courseName, String description) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
    }
}
