package com.roze.service;

import com.roze.StudentReactiveRepository;
import com.roze.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class StudentService {
    @Autowired
    private StudentReactiveRepository studentReactiveRepository;

    public Flux<Student> getAllStudents() {
//        return studentReactiveRepository.findAll();
        return studentReactiveRepository.findAllStudents();
    }
}
