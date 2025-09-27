package com.roze.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse<T> {
    private Boolean success;
    private String message;
    private T data;
    private Instant timestamp;
    private Integer statusCode;
}
