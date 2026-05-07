package com.sparta.apidev.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="trainees")
public class Trainee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "traineeID", nullable = false)
    private Integer traineeID;

    @Column(name = "Trainee Name", length = 50)
    private String traineeName;

    @Column(name = "DoB", length = 10)
    private String dob;

    @Column(name = "Email", length = 40)
    private String email;

    @Column(name = "Title", length = 10)
    private String title;

    @ManyToMany
    @JoinTable(
            name = "StudentCourse",
            joinColumns = @JoinColumn(name = "Student ID"),
            inverseJoinColumns = @JoinColumn(name = "Course ID")
    )
    private Set<Course> traineeCourse = new HashSet<>();

    public Trainee(String name, String dob, String email, String title) {
        this.traineeName = name;
        this.dob = dob;
        this.email = email;
        this.title = title;
    }

    public Trainee() {
        
    }

    public String getTraineeName() {
        return traineeName;
    }

    public void setTraineeName(String traineeName) {
        this.traineeName = traineeName;
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

    public void addCourse(Course course) {
        traineeCourse.add(course);
    }

    public void dropCourse(Course course) {
        traineeCourse.remove(course);
    }

    public Set<Course> getCourses() {
        return traineeCourse;
    }

    public int getID() {
        return this.traineeID;
    }

    public void setID(int id) {
        this.traineeID = id;
    }

}
