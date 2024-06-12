package com.roze.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
//@Table(name = "AUTHOR_TBL")
public class Author extends BaseEntity {
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
    //we use type as Integer instead of int because by default int=0 and Integer=null
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

    @Column(
            nullable = false,
            length = 35)
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private int age;
    @ManyToMany(mappedBy = "authors")
    private List<Course> courses;
}
