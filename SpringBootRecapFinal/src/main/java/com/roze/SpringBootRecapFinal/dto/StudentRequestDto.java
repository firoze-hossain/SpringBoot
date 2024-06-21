package com.roze.SpringBootRecapFinal.dto;

public record StudentRequestDto(
        String firstName,

        String lastName,

        String email,

        Integer schoolId
) {
}
