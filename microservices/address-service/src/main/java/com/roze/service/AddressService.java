package com.roze.service;

import com.roze.entity.Address;
import com.roze.repository.AddressRepository;
import com.roze.response.AddressResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ModelMapper modelMapper;

    public AddressResponse getAddressByEmployee(int employeeId) {
        Address address = addressRepository.findAddressByEmployeeId(employeeId);
        AddressResponse response = modelMapper.map(address, AddressResponse.class);
        return response;
    }
}
