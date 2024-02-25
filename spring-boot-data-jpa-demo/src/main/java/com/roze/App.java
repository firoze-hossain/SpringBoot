package com.roze;

import com.roze.dao.StudentDAO;
import com.roze.entity.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext container = SpringApplication.run(App.class, args);
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
        System.out.println("*******************");
        Optional<Student> student1 = studentDAO.findById(2);
        if (student1.isPresent()) {
            System.out.println(student1.get());
        }

    }
}
