package com.example.springdatajpa.Repository;

import com.example.springdatajpa.Model.Course;
import com.example.springdatajpa.Model.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TeacherRepositoryTest {

    private final TeacherRepository teacherRepository;

    @Autowired
    TeacherRepositoryTest(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Test
    public void saveTeacher(){
        Course courseLSD = Course.builder()
                .title("LSD")
                .credit(5)
                .build();

        Course courseSO = Course.builder()
                .title("SO")
                .credit(6)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Joe")
                .lastName("Khan")
                //.courses(List.of(courseLSD, courseSO)) am comentat relatia de @OneToMany din Teacher si deaia da eroare daca nu comentez
                .build();

        teacherRepository.save(teacher);
    }
}