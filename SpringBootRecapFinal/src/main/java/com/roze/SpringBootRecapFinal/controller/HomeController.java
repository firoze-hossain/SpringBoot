package com.roze.SpringBootRecapFinal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "Welcome to Java Home";
    }

    @GetMapping("/profile")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String profile() {
        return "Welcome to Firoze's Profile";
    }
}
