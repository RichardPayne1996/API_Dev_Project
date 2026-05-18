package com.sparta.apidev.dtos;



import java.time.LocalDate;

public class TrainerDTO {
    private int trainerId;
    private String trainerName;
    private String trainerEmail;
    private LocalDate trainerDob;
    private String trainerTitle;
    private boolean trainer;

    public TrainerDTO(int trainerId, String trainerName, String trainerEmail, LocalDate trainerDoB, String trainerTitle, boolean trainer) {
        this.trainerId = trainerId;
        this.trainerName = trainerName;
        this.trainerEmail = trainerEmail;
        this.trainerDob = trainerDoB;
        this.trainerTitle = trainerTitle;
        this.trainer = trainer;
    }
    public TrainerDTO(){}
    
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

    public LocalDate getTrainerDob() {
        return trainerDob;
    }

    public boolean isTrainer() {
        return this.trainer;
    }

    public void setTrainer(boolean isTrainer) {
        this.trainer = isTrainer;
    }

    public void setTrainerDob(LocalDate trainerDoB) {
        this.trainerDob = trainerDoB;
    }

    public String getTrainerTitle() {
        return trainerTitle;
    }

    public void setTrainerTitle(String trainerTitle) {
        this.trainerTitle = trainerTitle;
    }
}