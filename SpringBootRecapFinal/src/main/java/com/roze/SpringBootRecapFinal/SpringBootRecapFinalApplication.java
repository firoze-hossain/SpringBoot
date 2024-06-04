package com.roze.SpringBootRecapFinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootRecapFinalApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootRecapFinalApplication.class, args);
        //this is not a good practice
        //we should use the core feature of spring
//        FirstClass firstClass = new FirstClass();
//        System.out.println(firstClass.hello());
//Used the core feature of spring
        //then get bean from spring application context
        FirstClass firstClass = context.getBean(FirstClass.class);
        System.out.println(firstClass.hello());

    }

    // I have created a bean of FirstClass using bean annotation
    @Bean
    public FirstClass firstClass() {
        return new FirstClass();
    }

}
