package com.roze.dao;

import com.roze.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentDAO extends JpaRepository<Student, Integer> {
}
