package com.sparta.apidev.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    private Integer id;

    @Column(name = "course_name", length = 50)
    private String courseName;

    @Column(name = "description", length = 255)
    private String description;

    @ManyToMany(mappedBy = "traineeCourse")
    private Set<Trainee> trainees = new HashSet<>();

    @ManyToMany(mappedBy = "teacherCourse")
    private Set<Trainer> trainers = new HashSet<>();

    public Course(String courseName, String description) {
        this.courseName = courseName;
        this.description = description;
    }

    public Course(){}

    // ID
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // courseName
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    // description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // trainees
    public Set<Trainee> getTrainees() {
        return trainees;
    }

    public void setTrainees(Set<Trainee> trainees) {
        this.trainees = trainees;
    }

    // trainers
    public Set<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(Set<Trainer> trainers) {
        this.trainers = trainers;
    }

    public void addTrainee(Trainee trainee){
        trainees.add(trainee);
    }

    public void deleteTrainee(Trainee trainee){
        trainees.remove(trainee);
    }

    public void addTrainer(Trainer trainer){
        trainers.add(trainer);
    }

    public void deleteTrainer(Trainer trainer){
        trainers.remove(trainer);
    }
}
