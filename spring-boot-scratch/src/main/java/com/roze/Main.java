package com.roze;

import com.roze.autoconfigure.Course;
import com.roze.utility.College;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
//for custom folder and custom name of properties
//@PropertySource("myproperties/abc.properties")
//second way with classpath:
//@PropertySource("classpath:myproperties/abc.properties")
//for the root folder src/main/resources
//@PropertySource("classpath:abc.properties")
public class Main implements CommandLineRunner {
    @Autowired
    WebApplicationContext container;

    public static void main(String[] args) {
//        SpringApplication springApplication = new SpringApplication(Main.class);
//        springApplication.run(args);
        // we don't need to create SpringApplication instance to call run method
        ConfigurableApplicationContext container = SpringApplication.run(Main.class, args);
        MyApp myApp = container.getBean(MyApp.class);
        System.out.println("My app name: " + myApp.getAppName());

    }

    @Override
    public void run(String... args) throws Exception {
        Course course = container.getBean(Course.class);
        course.displayCourse();
    }

    //    @Bean
//    public College getCollegeObject() {
//        return new College();
//    }

}
