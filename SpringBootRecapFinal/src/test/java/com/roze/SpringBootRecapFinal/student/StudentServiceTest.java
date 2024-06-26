package com.roze.SpringBootRecapFinal.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_save_a_student() {
        //given
        StudentRequestDto studentRequestDto = new StudentRequestDto(
                "Firoze",
                "Hossain",
                "firoze@gmail.com",
                1);

        Student student = new Student();
        student.setFirstName("Firoze");
        student.setLastName("Hossain");
        student.setEmail("firoze@gmail.com");

        Student savedStudent = new Student();
        savedStudent.setId(1);
        savedStudent.setFirstName("Firoze");
        savedStudent.setLastName("Hossain");
        savedStudent.setEmail("firoze@gmail.com");


        Mockito.when(studentMapper.toStudent(studentRequestDto)).thenReturn(student);

        Mockito.when(studentRepository.save(student)).thenReturn(savedStudent);

        Mockito.when(studentMapper.toStudentResponseDto(savedStudent)).thenReturn(
                new StudentResponseDto(1, "Firoze", "Hossain", "firoze@gmail.com"));

        //when
        StudentResponseDto studentResponseDto = studentService.saveStudent(studentRequestDto);

        //then

        Assertions.assertEquals(studentRequestDto.firstName(), studentResponseDto.firstName());
        Assertions.assertEquals(studentRequestDto.lastName(), studentResponseDto.lastName());
        Assertions.assertEquals(studentRequestDto.email(), studentResponseDto.email());

    }
}