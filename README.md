# java-best-test

1)
1.1) @Test - 테스트를 실행할 Method를 지칭하는 어노테이션
1.2) @Disabled - 테스트 실행중 실행 시키지 않을 메소드를 지칭하기 위한 어노테이션
1.3) @BeforeAll - 클래스 내부에 있는 테스트 전부를 실행시키기 전 딱 1번 실행되는 어노테이션
1.4) @AfterAll - 클래스 내부에 있는 테스트 전부를 실행시킨 후 딱 1번 실행되는 어노테이션
1.5) @BeforeEach - 각 테스트 메소드를 실행하기전에 전처리 작업을 지정하는 어노테이션
1.6) @AfterEach - 각 테스트 메소드를 실행한 후에 전처리 작업을 지정하는 어노테이션

2) Junit 조건에 따른 테스트 실행
2.1) assumeTrue() - 특정 조건을 걸어놓고 해당 조건을 통과하지 않으면, 밑에 조건들은 실행할수 없게 한다.
2.2) assumingThat(조건, executable) - 조건을 걸어놓고, exectuable에는 () -> 값을 통한 테스트 하고 싶은 코드를 정의한다.
2.3) @EnabledOnOs - 특정 OS에만 실행 가능한 테스트를 만들때 사용하는 어노테이션.
2.4) @DisableOnOs - 특정 OS에만 실행 불가능한 테스트를 만들때 사용하는 어노테이션.
2.5) @EnabledOnJre - 특정 Jre 버전에만 실행 가능 테스트를 만들때 사용하는 어노테이션.
2.5) @DisabledOnJre - 특정 Jre 버전에만 실행 불가능한 테스트를 만들때 사용하는 어노테이션.
2.6) @EnabledIfEnvironmentVariable - 특정 실행 환경에만 사용가능한 테스트를 만들때 사용하는 어노테이션.
2.7) @DisabledIfEnvironmentVariable - 특정 실행 환경에만 사용 불가능한 테스트를 만들때 사용하는 어노테이션.