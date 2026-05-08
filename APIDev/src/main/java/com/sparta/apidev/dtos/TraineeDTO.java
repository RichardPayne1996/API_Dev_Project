package com.sparta.apidev.dtos;

import java.time.LocalDate;

public class TraineeDTO {
    private int traineeId;
    private String traineeName;
    private String traineeEmail;
    private LocalDate traineeDob;
    private String traineeTitle;

    public TraineeDTO(int traineeId, String traineeName, String traineeEmail, LocalDate traineeDOB, String traineeTitle) {
        this.traineeId = traineeId;
        this.traineeName = traineeName;
        this.traineeEmail = traineeEmail;
        this.traineeDob = traineeDOB;
        this.traineeTitle = traineeTitle;
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
}
