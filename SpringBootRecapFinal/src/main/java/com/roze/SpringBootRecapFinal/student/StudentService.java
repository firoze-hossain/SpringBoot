package com.roze.SpringBootRecapFinal.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository repository, StudentMapper studentMapper) {
        this.repository = repository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto saveStudent(StudentRequestDto requestDto) {
        Student student = studentMapper.toStudent(requestDto);
        Student savedStudent = repository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    public List<StudentResponseDto> findAllStudents() {
        return repository.findAll().stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto findById(Integer id) {
        return repository.findById(id)
                .map(studentMapper::toStudentResponseDto)
                .orElse(null);
    }

    public List<StudentResponseDto> findByFirstName(String firstName) {
        return repository.findAllByFirstNameContaining(firstName)
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public void deleteStudentById(Integer id) {
        repository.deleteById(id);
    }
}
