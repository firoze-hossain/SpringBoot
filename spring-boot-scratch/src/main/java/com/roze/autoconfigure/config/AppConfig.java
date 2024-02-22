package com.roze.autoconfigure.config;

import com.roze.autoconfigure.Course;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration//this will work fine
//but what about AutoConfiguration
@AutoConfiguration
public class AppConfig {
    @Bean
    public Course getCourse() {
        return new Course();
    }
}
