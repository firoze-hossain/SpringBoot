package com.roze.repository;

import com.roze.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    //query-named-params with native query
    @Query(value = "select * from tb_student s where s.first_name=:firstName and s.last_name=:lastName",
            nativeQuery = true)
    Student getStudentByFirstAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    //modifying method only return void or int/Integer
    //for any modification of database like update delete we should we modifying annotation
    //for any transaction in data we should use Transactional annotation and commit data in database
    //best practice of use Transactional in service layer where all repository layers are called
    //Transactional can use method or class level
    @Modifying
    @Transactional
    @Query(
            value = "update tb_student set first_name=:firstName where email_address=:emailId",
            nativeQuery = true
    )
    void updateFirstNameByEmailAddress(@Param("firstName") String firstName, @Param("emailId") String emailId);
}
