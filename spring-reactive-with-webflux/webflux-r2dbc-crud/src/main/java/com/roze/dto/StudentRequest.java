package com.roze.dto;

import com.roze.entity.Student;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String name;
    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age must be >= 0")
    @Max(value = 150, message = "Age seems invalid")
    private Integer age;

    public Student toEntity() {
        return Student.builder()
                .id(null)
                .name(this.name)
                .age(this.age)
                .build();
    }

    public Student toUpdateEntity(Long id) {
        return Student.builder()
                .id(id)
                .name(this.name)
                .age(this.age)
                .build();
    }
}
