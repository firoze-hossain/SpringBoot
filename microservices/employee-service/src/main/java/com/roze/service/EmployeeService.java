package com.roze.service;

import com.roze.entity.Employee;
import com.roze.feignclient.AddressClient;
import com.roze.repository.EmployeeRepository;
import com.roze.request.AddressRequest;
import com.roze.request.EmployeeRequest;
import com.roze.response.AddressResponse;
import com.roze.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    private RestTemplate restTemplate;
    //    @Autowired
//    private DiscoveryClient discoveryClient;
//    @Autowired
//    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private AddressClient addressClient;
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
//        AddressResponse addressResponse = callAddressServiceUsingWebClient(id);
//        AddressResponse addressResponse =addressClient.getAddressByEmployeeId(id);
        ResponseEntity<AddressResponse> addressResponseResponseEntity = addressClient.getAddressByEmployeeId(id);
        AddressResponse addressResponse = addressResponseResponseEntity.getBody();
        response.setAddressResponse(addressResponse);
        return response;
    }


    private AddressResponse callAddressServiceUsingWebClient(int id) {
        return webClient
                .get()
                .uri("/address/" + id)
                .retrieve()
                .bodyToMono(AddressResponse.class)
                .block();
    }

    private AddressResponse getForObjectByRestTemplate(int id) {
//        //get the ip and port of address service using discovery client
//        List<ServiceInstance> instances = discoveryClient.getInstances("ADDRESS-SERVICE");
////        ServiceInstance serviceInstance = instances.get(0);
//        String uri = serviceInstance.getUri().toString();


        //get the ip and port  dynamically of address service using loadbalancer client
//        ServiceInstance serviceInstance = loadBalancerClient.choose("ADDRESS-SERVICE");
//        String uri = serviceInstance.getUri().toString();
//        String contextRoot = serviceInstance.getMetadata().get("configPath");
//        System.out.println("User==>" + serviceInstance.getMetadata().get("user"));
//        System.out.println("Uri====>" + uri + contextRoot);
        // return restTemplate.getForObject(uri + contextRoot + "/address/{id}", AddressResponse.class, id);
        return restTemplate.getForObject("http://ADDRESS-SERVICE/address-app/api/address/{id}", AddressResponse.class, id);
    }

    public EmployeeResponse create(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setBloodGroup(request.getBloodGroup());
        Employee savedEmployee = employeeRepository.save(employee);

        AddressRequest addressRequest = request.getAddressRequest();
        if (addressRequest != null) {
            AddressRequest addressRequestWithEmployeeId = new AddressRequest();
            addressRequestWithEmployeeId.setLane1(addressRequest.getLane1());
            addressRequestWithEmployeeId.setLane2(addressRequest.getLane2());
            addressRequestWithEmployeeId.setPostalCode(addressRequest.getPostalCode());
            addressRequestWithEmployeeId.setState(addressRequest.getState());

            addressClient.createAddress(savedEmployee.getId(), addressRequestWithEmployeeId);
        }
        EmployeeResponse response = modelMapper.map(savedEmployee, EmployeeResponse.class);
        return response;
    }

    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<AddressResponse> addressResponseList = Collections.emptyList();
        try {
            ResponseEntity<List<AddressResponse>> allAddresses = addressClient.getAllAddresses();
            addressResponseList = allAddresses.getBody() != null ? allAddresses.getBody() : Collections.emptyList();
        } catch (Exception e) {
            System.out.println("Error fetching all addresses: " + e.getMessage());
        }
        Map<Integer, AddressResponse> addressResponseMap = addressResponseList.stream().collect(Collectors.toMap(
                address -> getEmployeeIdFromAddress(address),
                address -> address
        ));
        return employees.stream().map(employee -> {
            EmployeeResponse response = modelMapper.map(employee, EmployeeResponse.class);
            AddressResponse addressResponse = addressResponseMap.get(employee.getId());
            response.setAddressResponse(addressResponse);
            return response;
        }).collect(Collectors.toList());
    }

    private int getEmployeeIdFromAddress(AddressResponse address) {
        return address.getEmployeeId();
    }
}
