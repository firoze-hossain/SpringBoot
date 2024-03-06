package com.roze.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @SequenceGenerator(name = "department_sequence",
            sequenceName = "department_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "department_sequence")
    private Long departmentId;
    @NotBlank(message = "Department name not be blank. Please Provide Department name!!")
    @NotNull(message = "Department name field should not be null!!")
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;

}
