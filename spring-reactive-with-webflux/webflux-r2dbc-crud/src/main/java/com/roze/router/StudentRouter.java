package com.roze.router;

import com.roze.handler.StudentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class StudentRouter {
    @Bean
    public RouterFunction<?> studentRoutes(StudentHandler handler) {
        return route(POST("/students"), handler::create)
                .andRoute(PUT("/students/{id}"), handler::update)
                .andRoute(GET("/students/{id}"), handler::getStudent)
                .andRoute(DELETE("/students/{id}"), handler::delete)
                .andRoute(GET("/students"), handler::getAllStudents);
    }
}
