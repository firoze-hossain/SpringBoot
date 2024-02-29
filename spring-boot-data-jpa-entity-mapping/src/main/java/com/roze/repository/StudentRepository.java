package com.roze.repository;

import com.roze.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByLastName(String firstName);

    List<Student> findByFirstNameContaining(String firstName);

    List<Student> findByLastNameNotNull();

    Student findByGuardianName(String name);
}
