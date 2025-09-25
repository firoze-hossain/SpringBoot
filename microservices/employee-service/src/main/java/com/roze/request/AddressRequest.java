package com.roze.request;

public class AddressRequest {
    private String lane1;
    private String lane2;
    private String postalCode;
    private String state;

    public AddressRequest() {
    }

    public AddressRequest(String lane1, String lane2, String postalCode, String state) {
        this.lane1 = lane1;
        this.lane2 = lane2;
        this.postalCode = postalCode;
        this.state = state;
    }

    public String getLane1() {
        return lane1;
    }

    public void setLane1(String lane1) {
        this.lane1 = lane1;
    }

    public String getLane2() {
        return lane2;
    }

    public void setLane2(String lane2) {
        this.lane2 = lane2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
