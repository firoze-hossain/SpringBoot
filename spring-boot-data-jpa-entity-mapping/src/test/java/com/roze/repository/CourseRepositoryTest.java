package com.roze.repository;

import com.roze.entity.Course;
import com.roze.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printAllCourses() {
        List<Course> courses = courseRepository.findAll();
        System.out.println(courses);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Abu Bakar")
                .lastName("Siddique")
                .build();
        Course course = Course.builder()
                .title("Spring")
                .credit(8)
                .teacher(teacher)
                .build();
        courseRepository.save(course);
    }

}