package com.roze.SpringBootRecapFinal.controller;

import com.roze.SpringBootRecapFinal.domain.Student;
import com.roze.SpringBootRecapFinal.repository.StudentRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        student.setCreatedAt(LocalDateTime.now());
        return repository.save(student);
    }
}
