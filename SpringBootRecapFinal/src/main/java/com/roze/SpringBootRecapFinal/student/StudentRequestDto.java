package com.roze.SpringBootRecapFinal.student;

public record StudentRequestDto(
        String firstName,

        String lastName,

        String email,

        Integer schoolId
) {
}
