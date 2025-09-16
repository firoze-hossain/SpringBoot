package com.roze.service;

import com.roze.entity.Employee;
import com.roze.repository.EmployeeRepository;
import com.roze.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

    public EmployeeResponse getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id).get();
//        EmployeeResponse response = new EmployeeResponse();
//        response.setId(employee.getId());
//        response.setEmail(employee.getEmail());
//        response.setBloodGroup(employee.getBloodGroup());
//        response.setName(employee.getName());
        EmployeeResponse response = modelMapper.map(employee, EmployeeResponse.class);
        return response;
    }
}
