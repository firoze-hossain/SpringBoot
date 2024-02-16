package com.roze;

import com.roze.utility.College;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
//        SpringApplication springApplication = new SpringApplication(Main.class);
//        springApplication.run(args);
        // we don't need to create SpringApplication instance to call run method
        SpringApplication.run(Main.class, args);

    }

//    @Bean
//    public College getCollegeObject() {
//        return new College();
//    }

}
