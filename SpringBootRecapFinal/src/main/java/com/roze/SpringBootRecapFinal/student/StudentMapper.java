package com.roze.SpringBootRecapFinal.student;

import com.roze.SpringBootRecapFinal.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toStudent(StudentRequestDto requestDto) {
        if (requestDto == null) {
            throw new NullPointerException("The dto should not be null");
        }
        Student student = new Student();
        student.setFirstName(requestDto.firstName());
        student.setLastName(requestDto.lastName());
        student.setEmail(requestDto.email());
        School school = new School();
        school.setId(requestDto.schoolId());
        student.setSchool(school);
        return student;
    }

    public StudentResponseDto toStudentResponseDto(Student student) {
        StudentResponseDto studentResponseDto = new StudentResponseDto(student.getId(),
                student.getFirstName(), student.getLastName(), student.getEmail());
        return studentResponseDto;
    }
}
