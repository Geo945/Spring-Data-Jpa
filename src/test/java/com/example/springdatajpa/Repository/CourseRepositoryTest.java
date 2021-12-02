package com.example.springdatajpa.Repository;

import com.example.springdatajpa.Model.Course;
import com.example.springdatajpa.Model.Student;
import com.example.springdatajpa.Model.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class CourseRepositoryTest {
    private final CourseRepository courseRepository;

    @Autowired
    CourseRepositoryTest(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Test
    public void printCourse(){
        List<Course> courses = courseRepository.findAll();
        System.out.println(courses);
        //how to get the courseMaterial
    }

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Joe")
                .lastName("Khan")
                .build();

        Course course = Course.builder()
                .title("Python")
                .credit(6)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination(){
        Pageable firstPagewithThreeRecords = PageRequest.of(1,4);
       // Pageable firstPagewithTwoRecords = PageRequest.of(1,2);

        List<Course> courses = courseRepository.findAll(firstPagewithThreeRecords).getContent();
        long totalElements = courseRepository.findAll(firstPagewithThreeRecords).getTotalElements();
        long totalPages = courseRepository.findAll(firstPagewithThreeRecords).getTotalPages();

        System.out.println("totalPages = " + totalPages);
        System.out.println("Total elements = " + totalElements);
        System.out.println("Courses = " + courses);
    }

    @Test//supose I want to sort on the title
    public void findAllSorting(){
        Pageable sortByTitle = PageRequest.of(0,4, Sort.by("title"));

        Pageable sortByCreditDesc = PageRequest.of(0,2,Sort.by("credit").descending());

        Pageable sortByTitleAndCreditDesc = PageRequest.of(0,2,Sort.by("title").descending().and(Sort.by("credit")));

        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();

        System.out.println("courses = " + courses);

    }

    @Test
    public void printfindByTitleContaining(){
        Pageable firstPageTenRecords=PageRequest.of(0,10);
        List<Course> courses = courseRepository.findByTitleContaining("P",firstPageTenRecords).getContent();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Mari")
                .lastName("Jonson")
                .build();

        Course course = Course.builder()
                .title("AI")
                .credit(4)
                .teacher(teacher)
                .build();

        Student student = Student.builder()
                .firstName("Patrocle")
                .emailId("patro@yahoo.com")
                .lastName("Nebunu")
                .build();

        course.addStudents(student);
        courseRepository.save(course);
    }

}