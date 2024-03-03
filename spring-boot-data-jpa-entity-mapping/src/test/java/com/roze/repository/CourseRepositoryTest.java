package com.roze.repository;

import com.roze.entity.Course;
import com.roze.entity.Student;
import com.roze.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Test
    public void printWithPagination() {
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);
        Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);
        List<Course> coursesWithThreeRecords = courseRepository.findAll(firstPageWithThreeRecords).getContent();
        List<Course> coursesWithTwoRecords = courseRepository.findAll(secondPageWithTwoRecords).getContent();
        long totalPagesWithThreeRecords = courseRepository.findAll(firstPageWithThreeRecords).getTotalPages();
        long totalElements = courseRepository.findAll(firstPageWithThreeRecords).getTotalElements();
        System.out.println("Courses with Three records: " + coursesWithThreeRecords);
        System.out.println("Total Elements: " + totalElements);
        System.out.println("Total Page with Three Records: " + totalPagesWithThreeRecords);
        System.out.println("*************************");
        long totalPagesWithTwoRecordsWithSecondPage = courseRepository.findAll(secondPageWithTwoRecords).getTotalPages();
        totalElements = courseRepository.findAll(secondPageWithTwoRecords).getTotalElements();

        System.out.println("Courses with two records: " + coursesWithTwoRecords);
        System.out.println("Total Elements: " + totalElements);
        System.out.println("Total Pages with two Records from second Page: " + totalPagesWithTwoRecordsWithSecondPage);

    }

    @Test
    public void printAllWithSorting() {
        Pageable sortByTitle = PageRequest.of(0, 3, Sort.by("title"));
        Pageable sortByCreditInDesc = PageRequest.of(0, 3, Sort.by("credit").descending());
        List<Course> coursesWithTitleWithAsc = courseRepository.findAll(sortByTitle).getContent();
        List<Course> courseWithCreditWithDesc = courseRepository.findAll(sortByCreditInDesc).getContent();
        System.out.println("Course sorted By Title in Asc: " + coursesWithTitleWithAsc);
        System.out.println("Course sorted by credit in desc: " + courseWithCreditWithDesc);
    }

    @Test
    public void printFindByTitleContaining() {
        Pageable pageable = PageRequest.of(0, 8);
        List<Course> courses = courseRepository.findByTitleContaining("J", pageable).getContent();
        System.out.println("Find By Title Containing J: " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Krishna")
                .lastName("Das")
                .build();
        Student studentAhmed = Student.builder()
                .firstName("Firoze")
                .lastName("Ahmed")
                .emailId("ahmed@gmail.com")
                .build();
        Course course = Course.builder()
                .title("Spring Data JPA")
                .credit(12)
                .teacher(teacher)
                .build();
        course.addStudents(studentAhmed);

        courseRepository.save(course);
    }
}