package com.roze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    //RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/{name}")
    public String create(@PathVariable String name) {
//        Map<String, String> userDetails = new HashMap<>();
//        userDetails.put("username", name);
//        String response = restTemplate.getForObject("http://localhost:8081/emails/sendemail/{username}", String.class, userDetails);
//        System.out.println("Email Sent to: " + response);
        kafkaTemplate.send("new-user-events", name);
        return "User created: " + name;
    }
}
