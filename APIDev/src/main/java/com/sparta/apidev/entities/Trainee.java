package com.sparta.apidev.entities;

import jakarta.persistence.*;
import org.springframework.context.annotation.Role;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="trainees")
public class Trainee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TraineeID", nullable = false)
    private int traineeID;

    @Column(name = "TraineeName", length = 50)
    private String traineeName;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        TRAINEE
    }

    @Column(name = "traineeDoB", length = 10)
    private LocalDate traineeDoB;

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

    public Trainee(String name, LocalDate dob, String email, String title, String password) {
        this.traineeName = name;
        this.traineeDoB = dob;
        this.traineeEmail = email;
        this.traineeTitle = title;
        this.password = password;
    }

    public Trainee() {
        
    }

    public int getTraineeId() {
        return this.traineeID;
    }

    public void setTraineeId(int id) {
        this.traineeID = id;
    }

    public String getTraineeName() {
        return traineeName;
    }

    public void setTraineeName(String traineeName) {
        this.traineeName = traineeName;
    }

    public LocalDate getTraineeDob() {
        return traineeDoB;
    }

    public void setTraineeDob(LocalDate dob) {
        this.traineeDoB = dob;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
