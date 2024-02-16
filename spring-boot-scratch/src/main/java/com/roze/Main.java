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
        SpringApplication springApplication = new SpringApplication(Main.class);
        ConfigurableApplicationContext context = springApplication.run();
        College college = context.getBean(College.class);
        System.out.println("************");
        System.out.println(college);
        college.printCollegeName();
    }

    @Bean
    public College getCollegeObject() {
        return new College();
    }

}
