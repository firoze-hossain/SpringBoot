package com.roze.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("emails")
public class EmailNotificationController {

    @GetMapping("/sendemail/{username}")
    public String sendEmail(@PathVariable String username) {
        System.out.println("Email sent");
        return "Email sent to user: " + username;
    }
}
