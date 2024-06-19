package com.roze.SpringBootRecapFinal.repository;

import com.roze.SpringBootRecapFinal.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
