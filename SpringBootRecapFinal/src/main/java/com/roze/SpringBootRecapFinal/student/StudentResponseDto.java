package com.roze.SpringBootRecapFinal.student;

public record StudentResponseDto(
        Integer id,

        String firstName,

        String lastName,

        String email
) {
}
