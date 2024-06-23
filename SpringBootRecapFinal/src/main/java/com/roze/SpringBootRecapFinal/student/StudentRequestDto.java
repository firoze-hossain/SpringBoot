package com.roze.SpringBootRecapFinal.student;

import jakarta.validation.constraints.NotEmpty;

public record StudentRequestDto(
        @NotEmpty
        String firstName,
        @NotEmpty
        String lastName,

        String email,

        Integer schoolId
) {
}
