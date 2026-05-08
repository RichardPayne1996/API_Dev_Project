package com.sparta.apidev.dtos;

public class CourseDTO {
    private Integer traineeId;
    private String courseName;
    private String description;

    public CourseDTO() {}

    public CourseDTO(Integer id, String courseName, String description) {
        this.traineeId = id;
        this.courseName = courseName;
        this.description = description;
    }

    public Integer getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(Integer id) {
        this.traineeId = id;
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
