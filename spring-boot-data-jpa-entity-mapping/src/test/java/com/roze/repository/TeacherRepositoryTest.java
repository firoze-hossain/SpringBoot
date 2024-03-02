package com.roze.repository;

import com.roze.entity.Course;
import com.roze.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {
        Course JavaCourse = Course.builder()
                .title("Java")
                .credit(6)
                .build();
        Course dsaCourse = Course.builder()
                .title("DSA")
                .credit(5)
                .build();
        Teacher teacher = Teacher.builder()
                .firstName("Alamgir")
                .lastName("Hossain")
                .courses(List.of(JavaCourse, dsaCourse))
                .build();
        teacherRepository.save(teacher);
    }

}