package com.roze.service;

import com.roze.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> getDepartmentList();

    Department getDepartmentById(Long departmentId);
}
