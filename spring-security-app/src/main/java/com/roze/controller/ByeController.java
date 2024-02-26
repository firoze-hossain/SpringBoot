package com.roze.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ByeController {
    @GetMapping("/bye")
    public String bye() {
        return "Bye all";
    }
}
