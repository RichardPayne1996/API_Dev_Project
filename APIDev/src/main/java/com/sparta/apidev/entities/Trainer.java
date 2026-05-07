package com.sparta.apidev.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trainers")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id", nullable = false)
    private Integer id;

    @Column(name = "trainer_name", length = 40)
    private String trainerName;

    @Column(name = "dob")
    private String dob;

    @Column(name = "email", length = 60)
    private String email;

    @Column(name = "title", length = 50)
    private String title;

    //@ManyToMany(mappedBy = "courses")
    //private List<Trainer> trainers = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "teaching",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    public Trainer(String trainerName, String dob, String email, String title) {
        this.trainerName = trainerName;
        this.dob = dob;
        this.email = email;
        this.title = title;
    }

    public Trainer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
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

    public List<Course> getCourses() {
        return this.courses;
    }
}