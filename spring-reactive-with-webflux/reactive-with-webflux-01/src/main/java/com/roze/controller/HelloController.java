package com.roze.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
    @ResponseBody
    @GetMapping("/hello")
    public List<String> hello() throws InterruptedException {
        List<String> returnList = new ArrayList<>();
        List<String> stringList = List.of("Firoze", "Millat", "Mydul", "Imtiaze");
        for (String str : stringList) {
            returnList.add(str);
            Thread.sleep(1000);
        }
        return returnList;
    }
}
