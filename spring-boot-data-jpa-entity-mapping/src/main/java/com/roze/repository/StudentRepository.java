package com.roze.repository;

import com.roze.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByLastName(String firstName);

    List<Student> findByFirstNameContaining(String firstName);

    List<Student> findByLastNameNotNull();

    Student findByGuardianName(String name);

    //JPQL query and "?1->means first parameter"
    //instead of using table name and column name we should use the class name and appropriate property name
    @Query("select s from Student s where s.emailId=?1")
    Student getStudentByEmailAddress(String email);

    @Query("select s.firstName from Student s where s.emailId=?1")
    String getFirstNameByEmailAddress(String email);

    //get student by guardian email address
    @Query("select s from Student s where s.guardian.email=?1 ")
    Student getStudentByGuardianEmailAddress(String email);

    //native query
    @Query(
            value = "select * from tb_student s where s.email_address=?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String email);
}
