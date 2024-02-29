package com.roze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseMaterial {
    @Id
    @SequenceGenerator(name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence")
    private Long courseMaterialId;
    private String url;
    @OneToOne(cascade = CascadeType.ALL)
    //cascade->give permission parent to child or give properties to child
    //it will first save the parent property in db, then child property with referencing parent property
    @JoinColumn(name = "course_id",
            referencedColumnName = "courseId")
    private Course course;
}
