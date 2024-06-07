package com.roze.SpringBootRecapFinal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    //    @Bean
    // @Bean(name = "firoze")
    @Bean()
    @Qualifier("bean1")
    public FirstClass firstBean() {
        return new FirstClass("Welcome in Java");
    }

    @Bean
    @Qualifier("bean2")
    public FirstClass secondBean() {
        return new FirstClass("Welcome in Spring boot");
    }
}
