package com.roze.SpringBootRecapFinal.controller;

import com.roze.SpringBootRecapFinal.domain.Student;
import com.roze.SpringBootRecapFinal.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping
    public List<Student> findAllStudents() {
        return repository.findAll();
    }

    @GetMapping("/{student-id}")
    public Student findById(@PathVariable("student-id") Integer id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/search/{first-name}")
    public List<Student> findByFirstName(@PathVariable("first-name") String firstName) {
        return repository.findAllByFirstNameContaining(firstName);
    }
}
