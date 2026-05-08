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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
