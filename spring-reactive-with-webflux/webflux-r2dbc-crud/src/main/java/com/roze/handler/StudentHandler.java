package com.roze.handler;

import com.roze.dto.StudentRequest;
import com.roze.service.StudentService;
import com.roze.utils.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StudentHandler {
    private final StudentService studentService;

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(StudentRequest.class)
                .flatMap(ValidationUtils::validate)
                .flatMap(studentService::create)
                .flatMap(resp -> ServerResponse.status(resp.getStatusCode())
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(resp));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return request.bodyToMono(StudentRequest.class)
                .flatMap(ValidationUtils::validate)
                .flatMap(req -> studentService.update(id, req))
                .flatMap(resp -> ServerResponse.status(resp.getStatusCode())
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(resp));

    }

    public Mono<ServerResponse> getStudent(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return studentService.getStudent(id)
                .flatMap(resp -> ServerResponse.status(resp.getStatusCode())
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(resp));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return studentService.delete(id)
                .flatMap(resp -> ServerResponse.status(resp.getStatusCode())
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(resp));
    }

    public Mono<ServerResponse> getAllStudents(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(studentService.getAllStudents(), Object.class);
    }
}
