package com.sparta.apidev.dtos;

public class CourseDTO {

    private Integer courseId;

    private String courseName;

    private String description;

    private int duration;

    public CourseDTO() {
    }

    public CourseDTO(
            Integer courseId,
            String courseName,
            String description,
            int duration
    ) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.duration = duration;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}