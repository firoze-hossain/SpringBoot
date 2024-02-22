package com.roze.autoconfigure.config;

import com.roze.autoconfigure.Course;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

//@Configuration//this will work fine
//but what about AutoConfiguration
@AutoConfiguration
//@ConditionalOnClass(name = "com.roze.entity.Roze")
//@ConditionalOnClass(value = Roze.class)
public class AppConfig {
   // @ConditionalOnBean(name = "college")
    @ConditionalOnBean(name = "bestcollege")
    @Bean
    public Course getCourse() {
        return new Course();
    }
}
