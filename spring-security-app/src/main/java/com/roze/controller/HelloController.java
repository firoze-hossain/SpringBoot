package com.roze.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello Hossain";
    }

    @GetMapping("/hi")
    public String hi() {
        return "hi firoze";
    }
}
