package com.roze.SpringBootRecapFinal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserRecord(

        String firstName,

        String lastName,

        String email

) {
}
