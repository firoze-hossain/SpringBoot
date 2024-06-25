package com.roze.SpringBootRecapFinal.student;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
    //    @BeforeAll
//    static void beforeAll() {
//        System.out.println("Before all method");
//    }
//
//    @AfterAll
//    static void afterAll() {
//        System.out.println("after all method");
//    }
//
//    @BeforeEach
//    void setUp() {
//        System.out.println("Before each method");
//    }
//
//    @AfterEach
//    void tearDown() {
//        System.out.println("After each method");
//    }
//
//    @Test
//    public void testMethod1() {
//        System.out.println("Test method one");
//    }
//
//    @Test
//    public void testMethod2() {
//        System.out.println("Test method two");
//    }
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent() {
        StudentRequestDto studentRequestDto = new StudentRequestDto(
                "Firoze",
                "Hossain",
                "firoze@gmail.com",
                1
        );
        Student student = studentMapper.toStudent(studentRequestDto);
        assertEquals(studentRequestDto.firstName(), student.getFirstName());
        assertEquals(studentRequestDto.lastName(), student.getLastName());
        assertEquals(studentRequestDto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(studentRequestDto.schoolId(), student.getSchool().getId());

    }
}