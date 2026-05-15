package com.sparta.apidev.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trainers")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TrainerID", nullable = false)
    private int trainerId;

    @Column(name = "TrainerName", length = 40)
    private String trainerName;

    @Column(name = "trainerDoB")
    private LocalDate trainerDob;

    @Column(name = "Email", length = 60)
    private String trainerEmail;

    @Column(name = "Title", length = 50)
    private String trainerTitle;
    @ManyToMany
    @JoinTable(
            name = "Teaching",
            joinColumns = @JoinColumn(name = "TrainerID"),
            inverseJoinColumns = @JoinColumn(name = "Course Id")
    )
    private Set<Course> teacherCourse = new HashSet<>();

    public Trainer(String trainerName, LocalDate trainerDoB, String email, String title) {
        this.trainerName = trainerName;
        this.trainerDob = trainerDoB;
        this.trainerEmail = email;
        this.trainerTitle = title;
    }

    public Trainer() {
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int id) {
        this.trainerId = id;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public LocalDate getTrainerDob() {
        return trainerDob;
    }

    public void setTrainerDob(LocalDate trainerDoB) {
        this.trainerDob = trainerDoB;
    }

    public String getTrainerEmail() {
        return trainerEmail;
    }

    public void setTrainerEmail(String email) {
        this.trainerEmail = email;
    }

    public String getTrainerTitle() {
        return trainerTitle;
    }

    public void setTrainerTitle(String title) {
        this.trainerTitle = title;
    }

    public Set<Course> getCourses() {
        return this.teacherCourse;
    }
}