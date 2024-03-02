package com.roze.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")
public class CourseMaterial {
    @Id
    @SequenceGenerator(name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence")
    private Long courseMaterialId;
    private String url;
    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false)

    //cascade->give permission parent to child or give properties to child
    //it will first save the parent property in db, then child property with referencing parent property
    @JoinColumn(name = "course_id",
            referencedColumnName = "courseId")
    private Course course;
}
