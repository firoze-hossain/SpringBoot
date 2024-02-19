package com.roze.controller;


import com.roze.entity.HelloWorld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello-world")
    public HelloWorld helloWorld() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setWord1("Welcome to");
        helloWorld.setWord2("spring.");
        return helloWorld;
    }
}
