package com.roze.SpringBootRecapFinal.controller;

import com.roze.SpringBootRecapFinal.domain.User;
import com.roze.SpringBootRecapFinal.domain.UserRecord;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @PostMapping("/users")
    public String createUser(@RequestBody String name) {
        return "new user created: " + name;

    }

    @PostMapping("users-new")
    public String createNewUser(@RequestBody User user) {
        return "new user created with: " + user.toString();
    }

    @PostMapping("users-new-record")
    public String createNewUserWithRecord(@RequestBody UserRecord user) {
        return "new user created with: " + user.toString();
    }
}