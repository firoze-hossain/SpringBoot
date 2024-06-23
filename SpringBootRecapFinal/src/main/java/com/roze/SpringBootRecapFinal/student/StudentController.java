package com.roze.SpringBootRecapFinal.student;

import jakarta.validation.Valid;
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
           @Valid @RequestBody StudentRequestDto requestDto
    ) {
        return studentService.saveStudent(requestDto);
    }

    @GetMapping
    public List<StudentResponseDto> findAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/{student-id}")
    public StudentResponseDto findById(@PathVariable("student-id") Integer id) {
        return studentService.findById(id);
    }

    @GetMapping("/search/{first-name}")
    public List<StudentResponseDto> findByFirstName(@PathVariable("first-name") String firstName) {
        return studentService.findByFirstName(firstName);
    }

    @DeleteMapping("/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentById(@PathVariable("student-id") Integer id) {
        studentService.deleteStudentById(id);
    }

}
