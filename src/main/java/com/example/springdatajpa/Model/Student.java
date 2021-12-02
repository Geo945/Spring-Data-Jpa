package com.example.springdatajpa.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data //form lombok generates getters,setter,equals,hashcode etc
@AllArgsConstructor//to generate constructor with all args
@NoArgsConstructor//to generate constructor with no args
@Builder//to add the builder parent for my entity class
@Table(
        name="tbl_student",
        uniqueConstraints = @UniqueConstraint(
                name = "emailid_unique",//so the email field is unique for every student
                columnNames = "email_address"//so the email field is unique for every student
        )
)
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "student_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long studentId;
    private String firstName;
    private String lastName;
    @Column(
            unique = true,
            name="email_address",
            nullable = false//every time we should be getting this values, if we are not getting it should throw an exception
    )//so this column will never be null(it should always have an unique value)
    private String emailId;//THIS should be unique for every student, so I want to define a particular constraint
    @Embedded
    private Guardian guardian;
}
