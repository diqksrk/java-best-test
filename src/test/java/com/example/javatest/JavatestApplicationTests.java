package com.example.javatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

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

    // 4번째.
    @FastTest
    void create_new_javatest4_1() {
        String test_env = System.getenv("TEST_ENV");
        System.out.println(test_env);
    }

    // 4번째.
    @SlowTest
    void create_new_javatest4_2() {
        String test_env = System.getenv("TEST_ENV");
        System.out.println(test_env);
    }

    @DisplayName("자바테스트 만들기")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeat_test(RepetitionInfo repetitionInfo) {
        System.out.println("test" + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("자바파라미터 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"날씨가", "많이", "춰워지고", "있네요"})
    @NullAndEmptySource
    void parameterized_test(String message) {
        System.out.println(message);
    }

    @DisplayName("자바파라미터 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"날씨가", "많이", "춰워지고", "있네요"})
    void csvSoruce_test(@ConvertWith(StudyConverter.class) Study study) {
        System.out.println(study.getLimit());
    }

    @DisplayName("자바파라미터3 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
    void csvSoruce_test2(Integer limit, String name) {
        Study study = new Study(limit, name);
        System.out.println(study.getLimit());
    }

    // 아규먼트 조합
    @DisplayName("자바파라미터3 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
    void csvSoruce_test3(ArgumentsAccessor argumentsAccessor) {
        Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        System.out.println(study.getLimit());
    }

    // 아규먼트 조합
    @DisplayName("자바파라미터3 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
    void csvSoruce_test5(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study.getLimit());
    }

    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
            return new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        }
    }

    // 하나의 아규먼트를 다른 타입으로 적용할때 변화되는 것.
    static class StudyConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
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
