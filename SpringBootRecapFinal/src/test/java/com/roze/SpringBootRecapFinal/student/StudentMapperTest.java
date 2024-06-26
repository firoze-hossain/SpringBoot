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

    @Test
    public void shouldMapStudentToStudentResponseDto() {
        Student student = new Student();
        student.setId(1);
        student.setFirstName("Firoze");
        student.setLastName("Hossain");
        student.setEmail("firoze@gmail.com");
        StudentResponseDto dto = studentMapper.toStudentResponseDto(student);
        assertEquals(dto.id(), student.getId());
        assertEquals(dto.firstName(), student.getFirstName());
        assertEquals(dto.lastName(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());

    }
    @Test
    public void should_throw_null_pointer_exception_when_dto_is_null(){
      Exception exp= assertThrows(NullPointerException.class,()->studentMapper.toStudent(null));
      assertEquals("The dto should not be null",exp.getMessage());

    }
}