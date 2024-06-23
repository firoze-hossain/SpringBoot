package com.roze.SpringBootRecapFinal.student;

import jakarta.validation.constraints.NotEmpty;

public record StudentRequestDto(
        @NotEmpty(message = "First name must not be empty nor null")
        String firstName,
        @NotEmpty(message = "Last name must not be empty nor null")
        String lastName,

        String email,

        Integer schoolId
) {
}
