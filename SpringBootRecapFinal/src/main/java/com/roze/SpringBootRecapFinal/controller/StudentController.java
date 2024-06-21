package com.roze.SpringBootRecapFinal.controller;

import com.roze.SpringBootRecapFinal.domain.School;
import com.roze.SpringBootRecapFinal.domain.Student;
import com.roze.SpringBootRecapFinal.dto.StudentRequestDto;
import com.roze.SpringBootRecapFinal.dto.StudentResponseDto;
import com.roze.SpringBootRecapFinal.mapper.StudentMapper;
import com.roze.SpringBootRecapFinal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public StudentResponseDto saveStudent(
            @RequestBody StudentRequestDto requestDto
    ) {
        Student student = toStudent(requestDto);
        Student savedStudent = repository.save(student);
        return toStudentResponseDto(savedStudent);


    }

    @GetMapping
    public List<StudentResponseDto> findAllStudents() {
        return repository.findAll().stream()
                .map(this::toStudentResponseDto)
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

    private StudentResponseDto toStudentResponseDto(Student student) {
        StudentResponseDto studentResponseDto = new StudentResponseDto(student.getId(),
                student.getFirstName(), student.getLastName(), student.getEmail());
        return studentResponseDto;
    }
    public Student toStudent(StudentRequestDto requestDto) {
        Student student = new Student();
        student.setFirstName(requestDto.firstName());
        student.setLastName(requestDto.lastName());
        student.setEmail(requestDto.email());
        School school = new School();
        school.setId(requestDto.schoolId());
        student.setSchool(school);
        return student;
    }
}
