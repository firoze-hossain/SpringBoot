package com.roze.request;

public class EmployeeRequest {
    private String name;
    private String email;
    private String bloodGroup;
    private AddressRequest addressRequest;

    public EmployeeRequest() {
    }

    public EmployeeRequest(String name, String email, String bloodGroup, AddressRequest addressRequest) {
        this.name = name;
        this.email = email;
        this.bloodGroup = bloodGroup;
        this.addressRequest = addressRequest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public AddressRequest getAddressRequest() {
        return addressRequest;
    }

    public void setAddressRequest(AddressRequest addressRequest) {
        this.addressRequest = addressRequest;
    }
}
