package com.roze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@Table(name = "AUTHOR_TBL")
public class Author {
    //    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//            generator = "author_sequence")
//    @SequenceGenerator(name = "author_sequence",
//            sequenceName = "author_sequence",
//            allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.TABLE,
//            generator = "author_id_gen")
//    @TableGenerator(name = "author_id_gen",
//            table = "id_generator",
//            pkColumnName = "id_name",
//            valueColumnName = "id_value",
//            allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//we use type as Integer instead of int because by default int=0 and Integer=null
    @Column(name = "f_name",
            nullable = false,
            length = 35)
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private int age;
    @ManyToMany(mappedBy = "authors")
    private List<Course> courses;
//    @Column(nullable = false, updatable = false)
//    private LocalDateTime createdAt;
//    @Column(insertable = false)
//    private LocalDateTime modifiedAt;
}
