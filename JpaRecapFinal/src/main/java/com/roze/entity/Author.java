package com.roze.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "author_sequence")
    @SequenceGenerator(name = "author_sequence",
            sequenceName = "author_sequence",
            allocationSize = 1)
    private Integer id;//we use type as Integer instead of int because by default int=0 and Integer=null
    private String firstName;
    private String lastName;
    private String email;
    private int age;
}
