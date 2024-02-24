package com.roze.dao;

import com.roze.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentDAOImpl  {
    @PersistenceContext(unitName = "mysqldb")
    EntityManager entityManager;

    @Transactional
    public void saveStudent(Student student) {
        entityManager.persist(student);
        System.out.println("Record saved successfully");

    }
}
