package com.sparta.apidev.dtos;



import com.sparta.apidev.enums.Role;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class TrainerDTO {
    private Integer trainerId;
    private String trainerName;
    private String trainerEmail;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate trainerDob;
    private String trainerTitle;
    private String role;

    public TrainerDTO(int trainerId, String trainerName, String trainerEmail, LocalDate trainerDoB, String trainerTitle, boolean trainer) {
        this.trainerId = trainerId;
        this.trainerName = trainerName;
        this.trainerEmail = trainerEmail;
        this.trainerDob = trainerDoB;
        this.trainerTitle = trainerTitle;
    }
    public TrainerDTO(){}
    
    public Integer getTrainerId() {
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


    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
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