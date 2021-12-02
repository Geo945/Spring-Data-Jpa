package com.example.springdatajpa.Repository;

import com.example.springdatajpa.Model.Course;
import com.example.springdatajpa.Model.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CourseMaterialRepositoryTest {

    private final CourseMaterialRepository courseMaterialRepository;

    @Autowired
    CourseMaterialRepositoryTest(CourseMaterialRepository courseMaterialRepository) {
        this.courseMaterialRepository = courseMaterialRepository;
    }

    @Test
    public void SaveCourseMaterial(){
        Course course= Course.builder()
                .title("PAA")
                .credit(6)
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.google.com")
                .course(course)
                .build();

        courseMaterialRepository.save(courseMaterial);//it will fail because there is no course in the database
        //to add a crouse, and to make this happen, cascading comes into help
        //after I add @OneToOne(
        //            cascade = CascadeType.ALL//so whatever the operations are, it will perform
        //    ) to class CourseMaterial
        //it will work
    }

    @Test
    public void printAllCourseMaterials(){
        List<CourseMaterial> materials = courseMaterialRepository.findAll();
        System.out.println(materials);//if fetch type is LAZY it will give error because it will try to print to course field among the other fields and the course was not fetched because it is lazy
        //if I want to pass the test with fetch type, I can use: @ToString(exclude = "course") in the CourseMaterial CLass so toString() will not print course so it will work
        //is fetch type is EAGER it will print the materials.
    }

}