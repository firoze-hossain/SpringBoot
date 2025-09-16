package com.roze.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
    @GetMapping("/address")
    public String getAddress() {
        return "Nikunja-2, Dhaka-1229";
    }
}
