package com.roze.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    RestTemplate restTemplate = new RestTemplate();

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/{name}")
    public String create(@PathVariable String name) {
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("username", name);
        String response = restTemplate.getForObject("http://localhost:8081/emails/sendemail/{username}", String.class, userDetails);
        System.out.println("Email Sent to: " + response);
        return "User created: " + name;
    }
}
