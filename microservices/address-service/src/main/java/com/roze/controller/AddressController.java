package com.roze.controller;

import com.roze.request.AddressRequest;
import com.roze.response.AddressResponse;
import com.roze.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/address/{employeeId}")
    public ResponseEntity<AddressResponse> createAddress(@PathVariable("employeeId") int employeeId, @RequestBody AddressRequest request) {
        AddressResponse response = addressService.create(employeeId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
