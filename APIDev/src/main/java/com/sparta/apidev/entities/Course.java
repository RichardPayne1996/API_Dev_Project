package com.sparta.apidev.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Course ID", nullable = false)
    private Integer id;


    @ManyToMany(mappedBy = "traineeCourse")
    private Set<Trainee> trainees = new HashSet<>();

    @ManyToMany(mappedBy = "TeacherCourse")
    private List<Trainer> trainers = new ArrayList<>();

    public Course() {
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
    }

    public Set<Trainee> getTrainees() {
        return trainees;
    }

    public void setTrainees(Set<Trainee> trainees) {
        this.trainees = trainees;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }
}

