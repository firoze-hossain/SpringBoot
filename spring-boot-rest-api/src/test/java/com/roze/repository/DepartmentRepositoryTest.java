package com.roze.repository;

import com.roze.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("CSE")
                .departmentAddress("Dhaka")
                .departmentCode("CSE-06")
                .build();
        entityManager.persist(department);
    }

    @Test
    @DisplayName("Get data by department id")
    public void getDepartmentByIdTest() {
        Department department = departmentRepository.findById(1L).get();
        System.out.println("hola: " + department.getDepartmentName());
        assertEquals(department.getDepartmentName(), "CSE");
    }
}