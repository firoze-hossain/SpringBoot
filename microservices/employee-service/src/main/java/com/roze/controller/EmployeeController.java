package com.roze.controller;

import com.roze.response.EmployeeResponse;
import com.roze.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EmployeeController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public String getEmployee() {
        String address = restTemplate.getForObject("http://localhost:8080/address", String.class);
        return "Name: Firoze, Email: firoze@gmail.com," + " Address: " + address;
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id) {
        EmployeeResponse response = employeeService.getEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
