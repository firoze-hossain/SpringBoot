package com.roze.controller;

import com.roze.entity.HelloWorld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public HelloWorld hello() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setWord1("Hello");
        helloWorld.setWord2("Spring Boot.");
        return helloWorld;

    }
}
