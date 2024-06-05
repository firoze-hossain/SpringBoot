package com.roze.SpringBootRecapFinal;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

//@Component
//@Service
//@Repository
public class FirstClass {
    private String myVar;

    public FirstClass(String myVar) {
        this.myVar = myVar;
    }

    public String hello() {
        return "Hello Firoze==>myVar=" + myVar;
    }

}
