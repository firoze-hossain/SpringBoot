package com.roze.controller;

import com.roze.model.Student;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class StudentController {
    @GetMapping(value = "/test", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> getAllIntegers() {
        return Flux.just(1, 2, 3, 4).delayElements(Duration.ofSeconds(1)).log();
    }

    @GetMapping(value = "/students", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> getAllStudents() {
        return Flux.just(
                new Student(1, "Firoze", 30)
                , new Student(2, "Hossain", 31)
                , new Student(3, "Millat", 28)
                , new Student(4, "Imtiaze", 30))
                .delayElements(Duration.ofSeconds(1))
                .log();
    }
}
