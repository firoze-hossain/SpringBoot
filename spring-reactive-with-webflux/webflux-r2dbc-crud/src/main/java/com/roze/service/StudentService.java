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
import java.util.List;

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

    public Mono<BaseResponse<StudentResponse>> getStudent(Long id) {
        return studentRepository.findById(id)
                .switchIfEmpty(Mono.error(new StudentNotFoundException(id)))
                .map(StudentResponse::toResponse)
                .map(resp -> BaseResponse.<StudentResponse>builder()
                        .success(true)
                        .message("Student fetched successfully")
                        .data(resp)
                        .timestamp(Instant.now())
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    public Mono<BaseResponse<Void>> delete(Long id) {
        return studentRepository.findById(id)
                .flatMap(student -> studentRepository.delete(student)
                        .thenReturn(BaseResponse.<Void>builder()
                                .success(true)
                                .message("Student deleted successfully")
                                .data(null)
                                .timestamp(Instant.now())
                                .statusCode(HttpStatus.NO_CONTENT.value())
                                .build()))
                .switchIfEmpty(Mono.error(new StudentNotFoundException(id)));
    }

    public Mono<BaseResponse<List<StudentResponse>>> getAllStudents() {
        return studentRepository.findAll()
                .map(StudentResponse::toResponse)
                .collectList()
                .map(list -> BaseResponse.<List<StudentResponse>>builder()
                        .success(true)
                        .message("Students fetched successfully")
                        .data(list)
                        .timestamp(Instant.now())
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }
}
