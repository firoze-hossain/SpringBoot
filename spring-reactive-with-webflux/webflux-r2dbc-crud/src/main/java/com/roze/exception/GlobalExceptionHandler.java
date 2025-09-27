package com.roze.exception;

import com.roze.base.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public Mono<BaseResponse<Void>> handleStudentNotFound(StudentNotFoundException e, ServerWebExchange exchange) {
        return Mono.just(BaseResponse.<Void>builder()
                .success(false)
                .message(e.getMessage())
                .data(null)
                .timestamp(Instant.now())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build()
        );
    }

    @ExceptionHandler(Exception.class)
    public Mono<BaseResponse<Void>> handleGenericException(Exception e, ServerWebExchange exchange) {
        return Mono.just(BaseResponse.<Void>builder()
                .success(false)
                .message("Internal server error: " + e.getMessage())
                .data(null)
                .timestamp(Instant.now())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build());
    }
}
