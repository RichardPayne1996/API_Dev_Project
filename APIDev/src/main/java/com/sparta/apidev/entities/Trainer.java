package com.sparta.apidev.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "trainers")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TrainerID", nullable = false)
    private Integer trainerID;

    @Column(name = "TrainerName", length = 40)
    private String trainerName;

    @Column(name = "TrainerDoB")
    private String trainerDoB;

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
    private Set<Course> TeacherCourse = new HashSet<>();

    public Trainer(String trainerName, String dob, String email, String title) {
        this.trainerName = trainerName;
        this.trainerDoB = dob;
        this.trainerEmail = email;
        this.trainerTitle = title;
    }

    public Trainer() {
    }

    public Integer getID() {
        return trainerID;
    }

    public void setID(Integer id) {
        this.trainerID = id;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getDoB() {
        return trainerDoB;
    }

    public void setDoB(String dob) {
        this.trainerDoB = dob;
    }

    public String getEmail() {
        return trainerEmail;
    }

    public void setEmail(String email) {
        this.trainerEmail = email;
    }

    public String getTitle() {
        return trainerTitle;
    }

    public void setTitle(String title) {
        this.trainerTitle = title;
    }

    public Set<Course> getCourses() {
        return this.TeacherCourse;
    }
}