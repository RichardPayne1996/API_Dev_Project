package com.sparta.apidev.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public class TraineeDTO {
    private int traineeId;
    private String traineeName;
    private String traineeEmail;
    private LocalDate traineeDob;
    private String traineeTitle;

    private boolean trainer;


    public TraineeDTO(int traineeId, String traineeName, String traineeEmail, LocalDate traineeDOB, String traineeTitle, boolean trainer) {
        this.traineeName = traineeName;
        this.traineeEmail = traineeEmail;
        this.traineeDob = traineeDOB;
        this.traineeTitle = traineeTitle;
        this.trainer = true;
    }
    public TraineeDTO() {}

    public int getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(int traineeId) {
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

    public boolean getTrainer() {
        return this.trainer;
    }

    public void setTrainer(boolean isTrainer) {
        this.trainer = isTrainer;
    }
}
