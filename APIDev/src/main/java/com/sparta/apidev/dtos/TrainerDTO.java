package com.sparta.apidev.dtos;

public class TrainerDTO {

    private int trainerId;
    private String trainerName;
    private String trainerEmail;
    private String trainerDOB;
    private String trainerTitle;

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getTrainerEmail() {
        return trainerEmail;
    }

    public void setTrainerEmail(String trainerEmail) {
        this.trainerEmail = trainerEmail;
    }

    public String getTrainerDOB() {
        return trainerDOB;
    }

    public void setTrainerDOB(String trainerDOB) {
        this.trainerDOB = trainerDOB;
    }

    public String getTrainerTitle() {
        return trainerTitle;
    }

    public void setTrainerTitle(String trainerTitle) {
        this.trainerTitle = trainerTitle;
    }
}