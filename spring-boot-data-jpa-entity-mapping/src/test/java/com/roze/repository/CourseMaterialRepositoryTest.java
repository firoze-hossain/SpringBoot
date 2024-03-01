package com.roze.repository;

import com.roze.entity.Course;
import com.roze.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {
    @Autowired
    private CourseMaterialRepository materialRepository;

    @Test
    public void saveCourseMaterial() {
        Course course = Course.builder()
                .title("Java")
                .credit(6)
                .build();
        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("firoze.vercel.app")
                .course(course)
                .build();
        materialRepository.save(courseMaterial);

    }

    @Test
    public void printAllCourseMaterial() {
        List<CourseMaterial> courseMaterials = materialRepository.findAll();
        System.out.println(courseMaterials);
    }

}