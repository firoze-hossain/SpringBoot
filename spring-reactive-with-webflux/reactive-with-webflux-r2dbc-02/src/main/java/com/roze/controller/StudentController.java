package com.roze.controller;

import com.roze.model.Student;
import com.roze.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RestController
@Component
public class StudentController {
    @Autowired
    private StudentService studentService;
//
//    @GetMapping(value = "/test", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<Integer> getAllIntegers() {
//        return Flux.just(1, 2, 3, 4).delayElements(Duration.ofSeconds(1)).log();
//    }

//    @GetMapping(value = "/students", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<Student> getAllStudents() {
//        return Flux.just(
//                new Student(1, "Firoze", 30)
//                , new Student(2, "Hossain", 31)
//                , new Student(3, "Millat", 28)
//                , new Student(4, "Imtiaze", 30))
//                .delayElements(Duration.ofSeconds(1))
//                .log();
//    }

//    @GetMapping(value = "/students", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<Student> getAllStudents() {
//        System.out.println("Thread which accepts the request: " + Thread.currentThread().getName());
//        Flux<Student> allStudents = studentService.getAllStudents();
//        return allStudents
//                .delayElements(Duration.ofSeconds(1))
//                .log();
//    }


    public Mono<ServerResponse> getAllStudents(ServerRequest serverRequest) {
        Flux<Student> allStudents = studentService.getAllStudents().log();

        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(allStudents, Student.class);
    }
}
