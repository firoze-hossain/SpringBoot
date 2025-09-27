package com.roze.service;

import com.roze.base.BaseResponse;
import com.roze.dto.StudentRequest;
import com.roze.dto.StudentResponse;
import com.roze.exception.StudentNotFoundException;
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

    public Mono<BaseResponse<StudentResponse>> update(Long id, StudentRequest request) {
        return studentRepository.findById(id)
                .flatMap(existing -> studentRepository.save(request.toUpdateEntity(id)))
                .map(StudentResponse::toResponse)
                .map(resp -> BaseResponse.<StudentResponse>builder()
                        .success(true)
                        .message("Student updated successfully")
                        .data(resp)
                        .timestamp(Instant.now())
                        .statusCode(HttpStatus.OK.value())
                        .build())
                .switchIfEmpty(Mono.error(new StudentNotFoundException(id)));
    }
}
