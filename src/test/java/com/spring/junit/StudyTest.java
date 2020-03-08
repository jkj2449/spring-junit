package com.spring.junit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {
    @Test
    @DisplayName("스터디 만들기")
    @Tag("fast")
    void create_new_study() {
        Study study = new Study(-10);
        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DRFAT여야 한다."),
                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능인원은 0보다 커야 된다.")
        );
    }

    @Test
    void create_new_study_throw() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        assertEquals("limit은 0보다 커야 된다.", exception.getMessage());
        System.out.println("create1");
    }

    @Test
    void create_new_study_timeout() {
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(1000);
        });
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    @DisabledOnOs(OS.MAC)
    @EnabledOnJre(JRE.JAVA_9)
    //@EnabledIfEnvironmentVariable(name = "TEST_ENV", matches = "LOCAL")
    void create_new_study_assume() {
        String env = "TEST_ENV";
        assumeTrue("LOCAL".equalsIgnoreCase(System.getenv(env))); // 해당 조건이 성립해야 다음 프로세스 실행.

        assumingThat("LOCAL".equalsIgnoreCase(System.getenv(env)), () -> {

        });

        Study study = new Study(-10);
        assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능인원은 0보다 커야 된다.");
    }

    @DisplayName("반복 테스트")
    @RepeatedTest(value = 10)
    void repeatTest(RepetitionInfo info) {
        System.out.println("repeat test " + info.getCurrentRepetition() + "/" + info.getTotalRepetitions() );
    }

    @ParameterizedTest
    @ValueSource(strings =  {"날씨가", "많이", "추워지고 있네요"})
    void parameterizedTest(String message) {
        System.out.println(message);
    }

    @ParameterizedTest
    @ValueSource(ints =  {10, 20, 30})
    void parameterizedTest(@ConvertWith(StudyConverter.class) Study study) {
        System.out.println(study.getLimit());
    }

    static class StudyConverter extends SimpleArgumentConverter {

        @Override
        protected Object convert(Object o, Class<?> aClass) throws ArgumentConversionException {
            assertEquals(Study.class, aClass, "Can only convert to Study");
            return new Study(Integer.parseInt(o.toString()));
        }
    }

    @BeforeAll // static method 사용, return type 없어야함, private 안됨.
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll // static method 사용, return type 없어야함, private 안됨.
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    @AfterEach
    void AfterEach() {
        System.out.println("after each");
    }
}