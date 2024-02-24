package com.roze;

import com.roze.config.AppConfig;
import com.roze.dao.StudentDAOImpl;
import com.roze.entity.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
        StudentDAOImpl studentDAOImpl = container.getBean("studentDAOImpl", StudentDAOImpl.class);
        Student student = new Student();
        student.setStudentName("Firoze");
        student.setAddress("Dhaka");
        student.setMobile(0171421620);
        studentDAOImpl.saveStudent(student);
    }
}
