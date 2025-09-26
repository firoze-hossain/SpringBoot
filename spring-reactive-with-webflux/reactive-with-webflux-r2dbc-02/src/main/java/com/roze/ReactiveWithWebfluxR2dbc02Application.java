package com.roze;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveWithWebfluxR2dbc02Application {
    //        implements CommandLineRunner {
    @Autowired
    private StudentReactiveRepository studentReactiveRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReactiveWithWebfluxR2dbc02Application.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        Flux<Student> students = studentReactiveRepository.findAll();
//        students.subscribe(student -> {
//            System.out.println(student);
//        });
//    }
}
