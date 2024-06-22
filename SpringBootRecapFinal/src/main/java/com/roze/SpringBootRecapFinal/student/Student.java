package com.roze.SpringBootRecapFinal.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.roze.SpringBootRecapFinal.studentProfile.StudentProfile;
import com.roze.SpringBootRecapFinal.school.School;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "f_name")
    private String firstName;
    @Column(length = 20)
    private String lastName;
    @Column(unique = true)
    private String email;
    private int age;
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private StudentProfile studentProfile;

    @ManyToOne
    @JoinColumn(name = "school_id")
    @JsonBackReference
    private School school;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
