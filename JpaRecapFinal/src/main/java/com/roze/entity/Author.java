package com.roze.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue
    private Integer id;//we use type as Integer instead of int because by default int=0 and Integer=null
    private String firstName;
    private String lastName;
    private String email;
    private int age;
}
