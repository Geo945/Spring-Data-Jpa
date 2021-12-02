package com.example.springdatajpa.Model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@ToString(exclude = "students")
public class Course {
    @Id
    @SequenceGenerator(
            name ="course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;
    private String title;
    private Integer credit;

    @OneToOne(
            mappedBy = "course",//telling that this mapping has been done by atribute Course course from CourseMaterial class
            fetch = FetchType.EAGER
    )
    private CourseMaterial courseMaterial;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )//many courses can be teached by same teacher
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )//la ManyToOne se adauga teacher_id in tabela clasei respective
    //la OneToMany se adauga nu se adauga in tabela clasei care face maparea ci in tabela obiectelor ce participa la relatia OneToMany
    private Teacher teacher;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(//we use JoinTable because on ManyToMany relation ship a table will be created. We don't use a @JoinColumn annotation like we do for @OneToOne and @ManyToOne
        name = "student_course_map",
        joinColumns = @JoinColumn(
                name = "course_id",
                referencedColumnName = "courseId"
        ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId"
            )
    )//create a table named "student_course_map" with 2 columns: course_id which contains the id of courss of this class and inverseJoinCOlumns is whatever i need to define for the student List, it refers of the student List I created below
    //inverseJoinColumns= The foreign key columns of the join table which reference the primary table of the entity that does not own the association
    private List<Student> students;

    public void addStudents(Student student){
        if(students == null) students = new ArrayList<>();
        students.add(student);
    }

}
