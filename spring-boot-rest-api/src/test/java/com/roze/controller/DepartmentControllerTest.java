package com.roze.controller;

import com.roze.entity.Department;
import com.roze.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DepartmentService departmentService;

    private Department department;


    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentId(1L)
                .departmentName("SE")
                .departmentCode("SE-06")
                .departmentAddress("Dhaka")
                .build();
    }

    @Test
    @DisplayName("Save department from end point")
    void saveDepartmentTest() throws Exception {
        Department departmentInput = Department.builder()
                .departmentName("SE")
                .departmentCode("SE-06")
                .departmentAddress("Dhaka")
                .build();
        Mockito.when(departmentService.saveDepartment(departmentInput)).thenReturn(department);
        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"departmentName\":\"SE\",\n" +
                        "    \"departmentAddress\":\"Dhaka\",\n" +
                        "    \"departmentCode\":\"SE-06\"\n" +
                        "}")).andExpect(status().isOk());
    }

    @Test
    void getDepartmentByIdTest() throws Exception {
        Mockito.when(departmentService.getDepartmentById(1L)).thenReturn(department);
        mockMvc.perform(MockMvcRequestBuilders.get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName")
                        .value(department.getDepartmentName()));
    }
}