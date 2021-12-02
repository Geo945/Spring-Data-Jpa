package com.example.springdatajpa.Repository;

import com.example.springdatajpa.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);

    //fetch students whos firstname contains given substring:
    List<Student> findByFirstNameContaining(String name);

    //gets the students whos lastname is not null
    List<Student> findByLastNameNotNull();

    //gets the students whos guardian names is eqauls to String name
    List<Student> findByGuardianName(String name);

    Student findByFirstNameAndLastName(String firstname, String lastname);

    //JPQL
    @Query("select s from Student s where s.emailId = ?1 ")
    Student getStudentByEmailAddress(String emailId);

    @Query("select s.firstName from Student s where s.emailId = ?1 ")
    String getStudentFirstNameByEmailAddress(String emailId);


    //Native sql queries
    @Query(
            value="select * from tbl_student s where s.email_address = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String emailID);

    //Native Named Param
    @Query(
            value="select * from tbl_student s where s.email_address = :em",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(@Param("em") String emailID);




    //UPDATE db
    //update student name by given email id
    @Modifying//to say this is a modifying query
    @Transactional//Because this is a modifying query wee need a transaction for it to modify the database data.
    //after this method is executed succesfully, the transaction will be commited to the database, otherwise it will not(in case of exceptions)
    //daca vreau sa modific continutul bazei de date fara sa folosesc un query, pot sa pun doar @Transactional, de exemplu daca dau Student stud = studentRepository.getStudentById(id) si apoi stud.setFirstName('name') nu mai trb modifyi pt ca nu folosesc query
    @Query(
            value="update tbl_student set first_name = ?1 where email_address = ?2 ",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName, String emailId);



}
