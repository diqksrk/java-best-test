package com.example.javatest;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class JavatestApplicationTests {

    @Test
    void create() {
        JavatestApplication dataStuctureApplication = new JavatestApplication();

        assertNotNull(dataStuctureApplication);
    }

    @Test
    @Disabled
    void disableTest() {
        System.out.println("disabled");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("BeforeEach");
    }

    @AfterEach
    void afterEach() {
        System.out.println("AfterEach");
    }
}
