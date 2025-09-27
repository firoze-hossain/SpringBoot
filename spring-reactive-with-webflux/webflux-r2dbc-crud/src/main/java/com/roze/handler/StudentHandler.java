package com.roze.handler;

import com.roze.dto.StudentRequest;
import com.roze.service.StudentService;
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
                .flatMap(studentService::create)
                .flatMap(resp -> ServerResponse.status(resp.getStatusCode())
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(resp));
    }
}
