package com.roze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(name = "users_sequence",
            sequenceName = "users_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "users_sequence")
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
