package com.sparta.apidev.dtos;

public class TraineeDTO {
    private int traineeId;
    private String traineeName;
    private String traineeEmail;
    private String traineeDOB;
    private String traineeTitle;

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

    public String getTraineeDOB() {
        return traineeDOB;
    }

    public void setTraineeDOB(String traineeDOB) {
        this.traineeDOB = traineeDOB;
    }

    public String getTraineeTitle() {
        return traineeTitle;
    }

    public void setTraineeTitle(String traineeTitle) {
        this.traineeTitle = traineeTitle;
    }
}
