package com.roze.SpringBootRecapFinal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MyFirstService {
    @Autowired
    @Qualifier("secondBean")
    private  FirstClass firstClass;

//    public MyFirstService(
//          // @Qualifier("bean2")
//          FirstClass firstClass
//    ) {
//        this.firstClass = firstClass;
//    }



    public String helloSpring() {
        return "Hello From dependency Injection: " + firstClass.hello();
    }
}
