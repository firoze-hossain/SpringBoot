package com.roze.SpringBootRecapFinal.dto;

public record StudentResponseDto(
        Integer id,

        String firstName,

        String lastName,

        String email
) {
}
