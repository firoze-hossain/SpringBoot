package com.roze.feignclient;

import com.roze.request.AddressRequest;
import com.roze.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//http://localhost:8080/address-app/api/address/1
//@FeignClient(name = "address-service", url = "http://localhost:8080/address-app/api")
//@FeignClient(name = "address-service", url = "http://localhost:8080", path = "/address-app/api")
//@FeignClient(name = "address-service", path = "/address-app/api")
//@RibbonClient(name = "address-service")
@FeignClient(name = "ADDRESS-SERVICE", path = "/address-app/api/")
public interface AddressClient {
    //    @GetMapping("/address/{id}")
//    AddressResponse getAddressByEmployeeId(@PathVariable("id") int id);
    @GetMapping("/address/{employeeId}")
    public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int id);

    @PostMapping("/address/{employeeId}")
    public ResponseEntity<AddressResponse> createAddress(@PathVariable("employeeId") int employeeId, @RequestBody AddressRequest request);
}
