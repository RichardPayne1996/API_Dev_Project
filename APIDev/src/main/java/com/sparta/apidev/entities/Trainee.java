package com.sparta.apidev.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="trainees")
public class Trainee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "traineeId", nullable = false)
    private int traineeId;

    @Column(name = "traineeName", length = 50)
    private String traineeName;

    @Column(name = "traineeDob", length = 10)
    private LocalDate traineeDob;

    @Column(name = "traineeEmail", length = 40)
    private String traineeEmail;

    @Column(name = "traineeTitle", length = 10)
    private String traineeTitle;

    @ManyToMany
    @JoinTable(
            name = "StudentCourse",
            joinColumns = @JoinColumn(name = "StudentID"),
            inverseJoinColumns = @JoinColumn(name = "CourseID")
    )
    private Set<Course> traineeCourse = new HashSet<>();

    public Trainee(String name, LocalDate dob, String email, String title) {
        this.traineeName = name;
        this.traineeDob = dob;
        this.traineeEmail = email;
        this.traineeTitle = title;
    }

    public Trainee() {
        
    }

    public int getTraineeId() {
        return this.traineeId;
    }

    public void setTraineeId(int id) {
        this.traineeId = id;
    }

    public String getTraineeName() {
        return traineeName;
    }

    public void setTraineeName(String traineeName) {
        this.traineeName = traineeName;
    }

    public LocalDate getTraineeDob() {
        return traineeDob;
    }

    public void setTraineeDob(LocalDate dob) {
        this.traineeDob = dob;
    }

    public String getTraineeEmail() {
        return traineeEmail;
    }

    public void setTraineeEmail(String email) {
        this.traineeEmail = email;
    }

    public String getTraineeTitle() {
        return traineeTitle;
    }

    public void setTraineeTitle(String title) {
        this.traineeTitle = title;
    }

    public void addCourse(Course course) {
        traineeCourse.add(course);
    }

    public void dropCourse(Course course) {
        traineeCourse.remove(course);
    }

    public Set<Course> getCourses() {
        return traineeCourse;
    }


}
