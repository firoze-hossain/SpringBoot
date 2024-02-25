//package com.roze.dao;
//
//import com.roze.entity.Student;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.TypedQuery;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Repository
//public class StudentDAOImpl {
//    //@PersistenceContext(unitName = "mysqldb")
//    @Autowired
//    EntityManager entityManager;
//
//    @Transactional
//    public void saveStudent(Student student) {
//        entityManager.persist(student);
//        System.out.println("Record saved successfully");
//
//    }
//
//    public List<Student> findAllStudent() {
//        TypedQuery<Student> query = entityManager.createQuery(" from Student", Student.class);
//        List<Student> resultList = query.getResultList();
//        return resultList;
//    }
//}
