package com.example.springdatajpa.Repository;

import com.example.springdatajpa.Model.Guardian;
import com.example.springdatajpa.Model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest//this will impact you database(change it)
//@DataJpaTest//it will help you to test the repository layer and it will flush the data, won't let you modify database containts
class StudentRepositoryTest {


    private final StudentRepository studentRepository;

    @Autowired
    StudentRepositoryTest(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("Michael")
                .email("michael@gmail.com")
                .mobile("1234567899")
                .build();

        Student student = Student.builder()
                .firstName("Marian")
                .emailId("marian@yahoo.com")
                .lastName("Mircea")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printStudentsByFirstName(){
        List<Student> students = studentRepository.findByFirstName("Marian");
        System.out.println(students);
    }

    @Test
    public void printStudentsByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("Ma");
        System.out.println(students);

    }

    @Test
    public void printStudentsBaesOnGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("Michael");
        System.out.println(students);

    }

    @Test
    public void printAllStudent(){
        List<Student> studentList = studentRepository.findAll();
        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printgetStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("marian@yahoo.com");
        System.out.println(student);
    }

    @Test
    public void printgetStudentFirstNameByEmailAddress(){
        String firstname = studentRepository.getStudentFirstNameByEmailAddress("marian@yahoo.com");
        System.out.println(firstname);
    }

    @Test
    public void printgetStudentByEmailAddressNative(){
        Student student = studentRepository.getStudentByEmailAddressNative("marian@yahoo.com");
        System.out.println(student);
    }

    @Test
    public void printgetStudentByEmailAddressNativeNamedParam(){
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("marian@yahoo.com");
        System.out.println(student);
    }

    @Test
    public void updateStudentNameByEmailId(){
        studentRepository.updateStudentNameByEmailId("Mihai","marian@yahoo.com");
    }
}