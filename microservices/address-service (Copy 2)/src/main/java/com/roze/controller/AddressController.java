package com.roze.controller;

import com.roze.response.AddressResponse;
import com.roze.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/address")
    public String getAddress() {
        return "Nikunja-2, Dhaka-1229";
    }

    @GetMapping("/address/{employeeId}")
    public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int id) {
        AddressResponse response = addressService.getAddressByEmployee(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
