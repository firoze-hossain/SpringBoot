package com.roze.SpringBootRecapFinal.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
    @BeforeEach
    void setUp() {
        System.out.println("Before each method");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each method");
    }

    @Test
    public void testMethod1() {
        System.out.println("Test method one");
    }

    @Test
    public void testMethod2() {
        System.out.println("Test method two");
    }
}