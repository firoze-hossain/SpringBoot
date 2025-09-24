package com.roze.service;

import com.roze.entity.Employee;
import com.roze.repository.EmployeeRepository;
import com.roze.response.AddressResponse;
import com.roze.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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
    @Autowired
    private RestTemplate restTemplate;
    //    @Autowired
//    private DiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
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
        AddressResponse addressResponse = getForObjectByRestTemplate(id);
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
}
