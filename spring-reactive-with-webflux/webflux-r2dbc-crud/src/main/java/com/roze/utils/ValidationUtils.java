package com.roze.utils;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import reactor.core.publisher.Mono;


public class ValidationUtils {
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> Mono<T> validate(T object) {
        var violations = validator.validate(object);
        if (!violations.isEmpty()) {
            String message = violations.iterator().next().getMessage();
            return Mono.error(new IllegalArgumentException(message));
        }
        return Mono.just(object);
    }
}
