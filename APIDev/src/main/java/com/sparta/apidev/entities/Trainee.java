package com.sparta.apidev.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="trainees")
public class Trainee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "traineeid", nullable = false)
    private Integer traineeid;

    @Column(name="traineename", length = 50)
    private String traineename;

    @Column(name="dob", length = 10)
    private String dob;

    @Column(name="email", length = 40)
    private String email;

    @Column(name="title", length=10)
    private String title;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

    public Trainee(String name, String dob, String email, String title) {
        this.traineename = name;
        this.dob = dob;
        this.email = email;
        this.title = title;
    }

    public String getTraineename() {
        return traineename;
    }

    public void setTraineename(String traineename) {
        this.traineename = traineename;
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

    public void addCourse(Course course){
        courses.add(course);
    }

    public void dropCourse(Course course){
        courses.remove(course);
    }
}
