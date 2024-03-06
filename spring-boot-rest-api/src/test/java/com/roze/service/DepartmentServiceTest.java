package com.roze.service;

import com.roze.entity.Department;
import com.roze.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;
    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("CSE")
                .departmentCode("CSE-06")
                .departmentAddress("Dhaka")
                .build();
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("CSE")).thenReturn(department);
    }

    @Test
    @DisplayName("Get data using department name")
    public void getDepartmentByNameTest() {
        String departmentName = "CSE";
        Department department = departmentService.getDepartmentByName(departmentName);
        assertEquals(departmentName, department.getDepartmentName());

    }
}