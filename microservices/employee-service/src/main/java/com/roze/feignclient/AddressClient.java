//package com.roze.feignclient;
//
//import com.roze.response.AddressResponse;
//import org.springframework.cloud.netflix.ribbon.RibbonClient;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
////http://localhost:8080/address-app/api/address/1
////@FeignClient(name = "address-service", url = "http://localhost:8080/address-app/api")
////@FeignClient(name = "address-service", url = "http://localhost:8080", path = "/address-app/api")
//@FeignClient(name = "address-service", path = "/address-app/api")
//@RibbonClient(name = "address-service")
//public interface AddressClient {
//    @GetMapping("/address/{id}")
//    AddressResponse getAddressByEmployeeId(@PathVariable("id") int id);
//}
