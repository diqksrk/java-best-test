# java-best-test

1)
1.1) @Test - 테스트를 실행할 Method를 지칭하는 어노테이션
1.2) @Disabled - 테스트 실행중 실행 시키지 않을 메소드를 지칭하기 위한 어노테이션
1.3) @BeforeAll - 클래스 내부에 있는 테스트 전부를 실행시키기 전 딱 1번 실행되는 어노테이션
1.4) @AfterAll - 클래스 내부에 있는 테스트 전부를 실행시킨 후 딱 1번 실행되는 어노테이션
1.5) @BeforeEach - 각 테스트 메소드를 실행하기전에 전처리 작업을 지정하는 어노테이션
1.6) @AfterEach - 각 테스트 메소드를 실행한 후에 전처리 작업을 지정하는 어노테이션

DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) - undersocre를 바꿔준다.

2) Junit 조건에 따른 테스트 실행
2.1) assumeTrue() - 특정 조건을 걸어놓고 해당 조건을 통과하지 않으면, 밑에 조건들은 실행할수 없게 한다.
2.2) assumingThat(조건, executable) - 조건을 걸어놓고, exectuable에는 () -> 값을 통한 테스트 하고 싶은 코드를 정의한다.
2.3) @EnabledOnOs - 특정 OS에만 실행 가능한 테스트를 만들때 사용하는 어노테이션.
2.4) @DisableOnOs - 특정 OS에만 실행 불가능한 테스트를 만들때 사용하는 어노테이션.
2.5) @EnabledOnJre - 특정 Jre 버전에만 실행 가능 테스트를 만들때 사용하는 어노테이션.
2.5) @DisabledOnJre - 특정 Jre 버전에만 실행 불가능한 테스트를 만들때 사용하는 어노테이션.
2.6) @EnabledIfEnvironmentVariable - 특정 실행 환경에만 사용가능한 테스트를 만들때 사용하는 어노테이션.
2.7) @DisabledIfEnvironmentVariable - 특정 실행 환경에만 사용 불가능한 테스트를 만들때 사용하는 어노테이션.

3) junit tagging과 filtering
3.1) @Tag("test") - 해당 어노테이션을 이용하면, 해당 test에 대한 부연설명을 달 수 있다.
3.2) 위에 붙여진 Tag를 이용하여, edit configuration에서 tags에 설정값을 넣어 해당 어노테이션만 붙은 메소드만 실행시키는 환경을 만들수 있다.
3.3) 로컬 환경에서 돌리는 테스트와, CI환경에서 돌리는 테스트를 다르게 하고 싶다면, pom.xml설정파일에서 profiles에 설정값을 주어 설정할수 있다.
3.4) 돌릴때는 -p 옵션을 줘서 설정할수 있다. ex) ./mvnw test -P ci

4) test Custom tag
4.1) annotation interface를 만든 후, 해당 어노테이션에 해당 어노테이션을 붙인다.
4.2) target - 누구한테 적용할지를 묻는 키워드로, 메소드에만 적용하고 싶다면 ElementType.Method로 칭한다.
4.3) Retention - 언제까지 해당 어노테이션을 적용할지 묻는 키워드로, 런타임에도 적용하고 싶다면 RetentionPolicy.Runtime이라 칭한다.
4.4) @Test, @Tag 어노테이션까지 붙인후, 커스텀 에노테이션을 적용한다.

5) test 반복하기
5.1) @RepeatedTest - 테스트를 반복하고 싶을때, 사용하는 어노테이션이다.
 속성으로는 name, value가 존재하며, value는 몇번 반복할지, name은 해당 테스트 메소드의 이름을 뭘로 할지를 정할수가 있다.
5.2) @ParameterizedTest - 반복되는 변수들을 하나의 메소드 안에서 통합하고자 할때 사용하는 메소드이다.
5.3) @ValueSource - 반복되는 변수들을 밸류를 넣을때 사용하는 어노테이션이다.
5.4) @NullAndEmptySource - 널값이나 빈값을 넣어주고자 할때 사용하는 어노테이션이다.
5.5) @CsvSource - 2개 이상의 변수를 테스트 할때 사용하는 어노테이션이다.
5.6) @ConvertWith - 위에서 테스트를 할때, Converter를 만드는데, 해당 Converter를 적용하고자 할때 사용하는 어노테이션이다.
5.7) @AggregateWith - 2개 이상의 변수를 테스트할때, 사용하는 어노테이션이다. 1개일 경우 Converterwith이다.