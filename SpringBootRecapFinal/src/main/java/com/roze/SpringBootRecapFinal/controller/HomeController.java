package com.roze.SpringBootRecapFinal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/name")
    public String postName(@RequestBody String name) {
        return "Request accepted with " + name;
    }
}
