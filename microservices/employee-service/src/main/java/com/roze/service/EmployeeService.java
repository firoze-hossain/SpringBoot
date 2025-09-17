package com.roze.service;

import com.roze.entity.Employee;
import com.roze.repository.EmployeeRepository;
import com.roze.response.AddressResponse;
import com.roze.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RestTemplate restTemplate;

    public EmployeeResponse getEmployeeById(int id) {

        Employee employee = employeeRepository.findById(id).get();
        EmployeeResponse response = modelMapper.map(employee, EmployeeResponse.class);
        AddressResponse addressResponse = restTemplate.getForObject("http://localhost:8080/address-app/api/address/{id}", AddressResponse.class, id);
        response.setAddressResponse(addressResponse);
        return response;
    }
}
