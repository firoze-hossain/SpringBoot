package com.roze.service;

import com.roze.base.BaseResponse;
import com.roze.dto.StudentRequest;
import com.roze.dto.StudentResponse;
import com.roze.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Mono<BaseResponse<StudentResponse>> create(StudentRequest request) {
        return studentRepository.save(request.toEntity())
                .map(StudentResponse::toResponse)
                .map(resp -> BaseResponse.<StudentResponse>builder()
                        .success(true)
                        .message("Student created successfully")
                        .data(resp)
                        .timestamp(Instant.now())
                        .statusCode(HttpStatus.CREATED.value())
                        .build());
    }
}
