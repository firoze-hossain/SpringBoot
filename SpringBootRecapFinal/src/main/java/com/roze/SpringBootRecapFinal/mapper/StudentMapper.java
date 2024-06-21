package com.roze.SpringBootRecapFinal.mapper;

import com.roze.SpringBootRecapFinal.domain.School;
import com.roze.SpringBootRecapFinal.domain.Student;
import com.roze.SpringBootRecapFinal.dto.StudentRequestDto;
import com.roze.SpringBootRecapFinal.dto.StudentResponseDto;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public  Student toStudent(StudentRequestDto requestDto) {
        Student student = new Student();
        student.setFirstName(requestDto.firstName());
        student.setLastName(requestDto.lastName());
        student.setEmail(requestDto.email());
        School school = new School();
        school.setId(requestDto.schoolId());
        student.setSchool(school);
        return student;
    }

    public  StudentResponseDto toStudentResponseDto(Student student) {
        StudentResponseDto studentResponseDto = new StudentResponseDto(student.getId(),
                student.getFirstName(), student.getLastName(), student.getEmail());
        return studentResponseDto;
    }
}
