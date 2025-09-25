package com.roze.service;

import com.roze.entity.Address;
import com.roze.repository.AddressRepository;
import com.roze.request.AddressRequest;
import com.roze.response.AddressResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ModelMapper modelMapper;

    public AddressResponse getAddressByEmployee(int employeeId) {
        System.out.println("finding address for employee " + employeeId + " on port 8080");
        Address address = addressRepository.findByEmployeeId(employeeId);
        AddressResponse response = modelMapper.map(address, AddressResponse.class);
        return response;
    }

    public AddressResponse create(int employeeId, AddressRequest request) {
        Address existingAddress = addressRepository.findByEmployeeId(employeeId);
        if (existingAddress != null) {
            throw new RuntimeException("Address already exists for employee id: " + employeeId);
        }
        Address address = new Address();
        address.setEmployeeId(employeeId);
        address.setLane1(request.getLane1());
        address.setLane2(request.getLane2());
        address.setPostalCode(request.getPostalCode());
        address.setState(request.getState());
        Address savedAddress = addressRepository.save(address);
        return modelMapper.map(savedAddress, AddressResponse.class);
    }

    public List<AddressResponse> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream().map(address -> modelMapper
                .map(address, AddressResponse.class))
                .collect(Collectors.toList());
    }
}
