package com.sparta.apidev.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class TraineeDTO {
    private Integer traineeId;
    private String traineeName;
    private String traineeEmail;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate traineeDob;
    private String traineeTitle;
    private String role;
    private Set<CourseDTO> courses = new HashSet<>();
    private Integer selectedCourseId;


    public TraineeDTO(int traineeId, String traineeName, String traineeEmail, LocalDate traineeDOB, String traineeTitle, boolean trainer) {
        this.traineeName = traineeName;
        this.traineeEmail = traineeEmail;
        this.traineeDob = traineeDOB;
        this.traineeTitle = traineeTitle;
    }
    public TraineeDTO() {}

    public Integer getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(Integer traineeId) {
        this.traineeId = traineeId;
    }

    public String getTraineeName() {
        return traineeName;
    }

    public void setTraineeName(String traineeName) {
        this.traineeName = traineeName;
    }

    public String getTraineeEmail() {
        return traineeEmail;
    }

    public void setTraineeEmail(String traineeEmail) {
        this.traineeEmail = traineeEmail;
    }

    public LocalDate getTraineeDob() {
        return traineeDob;
    }

    public void setTraineeDob(LocalDate traineeDOB) {
        this.traineeDob = traineeDOB;
    }

    public String getTraineeTitle() {
        return traineeTitle;
    }

    public void setTraineeTitle(String traineeTitle) {
        this.traineeTitle = traineeTitle;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String isTrainer) {
        this.role = isTrainer;
    }

    public Set<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseDTO> courses) {
        this.courses = courses;
    }

    public Integer getSelectedCourseId() {
        return selectedCourseId;
    }

    public void setSelectedCourseId(Integer selectedCourseId) {
        this.selectedCourseId = selectedCourseId;
    }
}
