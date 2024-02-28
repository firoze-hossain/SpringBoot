package com.roze.repository;

import com.roze.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .firstName("Firoze")
                .lastName("Hossain")
                .emailId("firoze@gmail.com")
                .guardianName("Abu Bakar")
                .getGuardianMobile("017777777")
                .guardianEmail("bakar@gmail.com").build();
        studentRepository.save(student);
    }

    @Test
    public void studentList() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println("Student List: " + studentList);
    }
}