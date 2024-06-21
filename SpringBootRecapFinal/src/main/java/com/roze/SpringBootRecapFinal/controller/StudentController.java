package com.roze.SpringBootRecapFinal.controller;

import com.roze.SpringBootRecapFinal.domain.Student;
import com.roze.SpringBootRecapFinal.dto.StudentRequestDto;
import com.roze.SpringBootRecapFinal.dto.StudentResponseDto;
import com.roze.SpringBootRecapFinal.mapper.StudentMapper;
import com.roze.SpringBootRecapFinal.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentRepository repository;
    private final StudentMapper studentMapper;

    public StudentController(StudentRepository repository, StudentMapper studentMapper) {
        this.repository = repository;
        this.studentMapper = studentMapper;
    }

    @PostMapping
    public StudentResponseDto saveStudent(
            @RequestBody StudentRequestDto requestDto
    ) {
        Student student = studentMapper.toStudent(requestDto);
        Student savedStudent = repository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    @GetMapping
    public List<StudentResponseDto> findAllStudents() {
        return repository.findAll().stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{student-id}")
    public Student findById(@PathVariable("student-id") Integer id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/search/{first-name}")
    public List<Student> findByFirstName(@PathVariable("first-name") String firstName) {
        return repository.findAllByFirstNameContaining(firstName);
    }

    @DeleteMapping("/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentById(@PathVariable("student-id") Integer id) {
        repository.deleteById(id);
    }

}
