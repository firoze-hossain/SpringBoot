package com.roze;

import com.roze.config.AppConfig;
import com.roze.dao.StudentDAO;
import com.roze.entity.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
        StudentDAO studentDAO = container.getBean("studentDAO", StudentDAO.class);
        Student student = new Student();
        student.setStudentName("Millat");
        student.setAddress("nikunjo");
        student.setMobile(01611);
        studentDAO.save(student);
        //  studentDAO.saveStudent(student);
        //List<Student> allStudent = studentDAO.findAllStudent();
        List<Student> allStudent = studentDAO.findAll();
        allStudent.forEach(stu -> System.out.println(stu));

    }
}
