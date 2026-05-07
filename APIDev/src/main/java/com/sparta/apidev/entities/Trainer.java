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
    @Column(name = "Trainer ID", nullable = false)
    private Integer trainerID;

    @Column(name = "Trainer Name", length = 40)
    private String trainerName;

    @Column(name = "DoB")
    private String dob;

    @Column(name = "Email", length = 60)
    private String email;

    @Column(name = "Title", length = 50)
    private String title;

    @ManyToMany
    @JoinTable(
            name = "Teaching",
            joinColumns = @JoinColumn(name = "Trainer ID"),
            inverseJoinColumns = @JoinColumn(name = "Course Id")
    )
    private Set<Course> TeacherCourse = new HashSet<>();

    public Trainer(String trainerName, String dob, String email, String title) {
        this.trainerName = trainerName;
        this.dob = dob;
        this.email = email;
        this.title = title;
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
        return dob;
    }

    public void setDoB(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Course> getCourses() {
        return this.TeacherCourse;
    }
}