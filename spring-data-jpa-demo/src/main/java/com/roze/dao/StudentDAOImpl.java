package com.roze.dao;

import com.roze.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAOImpl implements StudentDAO {
    @Autowired
    EntityManager entityManager;

    @Override
    public void savStudent(Student student) {
        entityManager.persist(student);
        System.out.println("Record saved successfully");

    }
}
