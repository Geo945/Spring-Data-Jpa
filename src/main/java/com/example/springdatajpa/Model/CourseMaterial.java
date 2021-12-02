package com.example.springdatajpa.Model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")
public class CourseMaterial {

    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    //course material can't exists itself
    //a course material exists if the course exists
    //so it's OneToOne relationship
    private Long courseMaterialId;
    private String url;

    @OneToOne(
            optional = false, // so whenever you try to save a courseMaterial, Course course is required, because we don't want a courseMaterial without a course(or with Course course=null), by default this is false
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL//so whatever the operations are, it will perform
    )
    @JoinColumn(//so this table (CourseMaterial) will have one more column (course_id) which defines the courseId
            name = "course_id",//the name of the column from database
            referencedColumnName = "courseId"//the name of the column from Course class
    )//which particular column it will be applied
    //course material face mapparea pt ca Course are mappedBy=course si astfel in tabela lui courseMaterial se adauga coloana "course_id"
    private Course course;
}
