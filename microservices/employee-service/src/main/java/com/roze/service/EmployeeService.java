package com.roze.service;

import com.roze.entity.Employee;
import com.roze.repository.EmployeeRepository;
import com.roze.response.AddressResponse;
import com.roze.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;
    //    @Autowired
//    private AddressClient addressClient;
    @Autowired
    private WebClient webClient;
    //@Autowired
    //private RestTemplate restTemplate;

//    @Value("${addressservice.base.url}")
//    private String addressBaseUrl;

//    @Autowired
//    public EmployeeService(@Value("${addressservice.base.url}") String addressBaseUrl, RestTemplateBuilder builder) {
//        System.out.println("uri: " + addressBaseUrl);
//        this.restTemplate = builder
//                .rootUri(addressBaseUrl)
//                .build();
//    }

    public EmployeeResponse getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id).get();
        EmployeeResponse response = modelMapper.map(employee, EmployeeResponse.class);
        //AddressResponse addressResponse = restTemplate.getForObject(addressBaseUrl + "/address/{id}", AddressResponse.class, id);
        //AddressResponse addressResponse = getForObjectByRestTemplate(id);
        AddressResponse addressResponse = webClient
                .get()
                .uri("/address/" + id)
                .retrieve()
                .bodyToMono(AddressResponse.class)
                .block();
//        AddressResponse addressResponse =addressClient.getAddressByEmployeeId(id);
        response.setAddressResponse(addressResponse);
        return response;
    }

//    private AddressResponse getForObjectByRestTemplate(int id) {
//        return restTemplate.getForObject("/address/{id}", AddressResponse.class, id);
//    }
}
