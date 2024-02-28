package com.roze.repository;

import com.roze.entity.Guardian;
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
//                .guardianName("Abu Bakar")
//                .guardianMobile("017777777")
//                .guardianEmail("bakar@gmail.com")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .name("ABu Bakar")
                .email("abub@gmail.com")
                .mobile("004444455")
                .build();
        Student student = Student.builder()
                .emailId("a@gmail.com")
                .firstName("Hossain")
                .lastName("Ahmed")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void studentList() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println("Student List: " + studentList);
    }
}