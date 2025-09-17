package com.roze.service;

import com.roze.entity.Employee;
import com.roze.repository.EmployeeRepository;
import com.roze.response.AddressResponse;
import com.roze.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;
    //@Autowired
    private RestTemplate restTemplate;

//    @Value("${addressservice.base.url}")
//    private String addressBaseUrl;

    @Autowired
    public EmployeeService(@Value("${addressservice.base.url}") String addressBaseUrl, RestTemplateBuilder builder) {
        System.out.println("uri: " + addressBaseUrl);
        this.restTemplate = builder
                .rootUri(addressBaseUrl)
                .build();
    }

    public EmployeeResponse getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id).get();
        EmployeeResponse response = modelMapper.map(employee, EmployeeResponse.class);
        //AddressResponse addressResponse = restTemplate.getForObject(addressBaseUrl + "/address/{id}", AddressResponse.class, id);
        AddressResponse addressResponse = restTemplate.getForObject( "/address/{id}", AddressResponse.class, id);
        response.setAddressResponse(addressResponse);
        return response;
    }
}
