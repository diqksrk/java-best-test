package com.example.javatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JavatestApplicationTests {

    @Test
    @DisplayName("자바테스트어플리케이션 만들기")
    void create_new_javatest() {
        Study study = new Study(-10);

        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(-10);
            Thread.sleep(300);
        });
        // TODO ThreadLocal -> 다른 쓰레드끼리 공유가 안된다. 기본전략 스프링의.

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = illegalArgumentException.getMessage();
        assertEquals(message, "limit는 0보다 커야 한다");

        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디는 무조건 " + StudyStatus.DRAFT + "이다."),
                () -> assertTrue(study.getLimit() > 0, () -> "스터디 참서 인원은 0보다 커야 합니다.")
        );
        // 람다식은 실패할경우만 연산을 하고, 그냥 메시지는 실행할때마다 연산을 실행한다.
    }

    @Test
    @DisplayName("자바테스트 어플리케이션2 만들기")
    // 테스트 실행할 OS 설정도 가능
    @EnabledOnOs( { OS.MAC, OS.LINUX })
    // 특정 자바 버전
    @EnabledOnJre({ JRE.JAVA_8 })
    // 실행 환경에서의 테스트
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "local")
    void create_new_javatest2() {
        String test_env = System.getenv("TEST_ENV");
        System.out.println(test_env);

        // assumeTrue로 테스트코드를 걸면, 해당 테스트 코드를 통과하지 않는한 뒤의 테스트 코드는 실행하지 않는다.
        assumeTrue("LOCAL".equalsIgnoreCase(test_env));
        // 마찬가지, 안에 있는걸 통과해야 람다 안에 있는 테스트 코드가 실행된다.
        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            System.out.println("das");
        });
    }

    // 3번째.
    @Test
    @DisplayName("자바테스트 어플리케이션2 만들기")
    // 태그를 붙여서 상세설명을 알려준다. - edit Configuration에서 tags를 설정해서 특정 tag붙인 애만 실행 할수 있다.
    @Tag("fast")
    void create_new_javatest3() {
        String test_env = System.getenv("TEST_ENV");
        System.out.println(test_env);
    }

    // 3번째.
    @Test
    @DisplayName("자바테스트 어플리케이션2 만들기")
    @Tag("slow")
    // 태그를 붙여서 상세설명을 알려준다.
    @Tag("fast")
    void create_new_javatest4() {
        String test_env = System.getenv("TEST_ENV");
        System.out.println(test_env);
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
