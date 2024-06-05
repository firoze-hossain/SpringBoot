package com.roze.SpringBootRecapFinal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
//    @Bean
    @Bean(name = "firoze")
    public FirstClass firstClass() {
        return new FirstClass("Welcome in Java");
    }
}
