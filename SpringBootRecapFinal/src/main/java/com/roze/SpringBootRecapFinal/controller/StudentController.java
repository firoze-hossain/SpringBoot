package com.roze.SpringBootRecapFinal.controller;

import com.roze.SpringBootRecapFinal.domain.Student;
import com.roze.SpringBootRecapFinal.dto.StudentRequestDto;
import com.roze.SpringBootRecapFinal.dto.StudentResponseDto;
import com.roze.SpringBootRecapFinal.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;

    }

    @PostMapping
    public StudentResponseDto saveStudent(
            @RequestBody StudentRequestDto requestDto
    ) {
        return studentService.saveStudent(requestDto);
    }

    @GetMapping
    public List<StudentResponseDto> findAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/{student-id}")
    public Student findById(@PathVariable("student-id") Integer id) {
        return studentService.findById(id);
    }

    @GetMapping("/search/{first-name}")
    public List<Student> findByFirstName(@PathVariable("first-name") String firstName) {
        return studentService.findByFirstName(firstName);
    }

    @DeleteMapping("/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentById(@PathVariable("student-id") Integer id) {
        studentService.deleteStudentById(id);
    }

}
