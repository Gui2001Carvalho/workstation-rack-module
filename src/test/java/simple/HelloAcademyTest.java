package simple;

import com.ctw.workstation.team.TeamResource;
import org.assertj.core.api.Assertions;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;


//ctrl + alt + v -> Cria a variavel

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HelloAcademyTest {

    public static Stream<Arguments> provide_valid_name1() {
        return Stream.of(
                Arguments.of("Rennan", "Hello Rennan"),
                Arguments.of("Maria", "Hello Maria"),
                Arguments.of("João", "Hello João")
        );
    }

    @Order(1)
    @Test
    @DisplayName("When providing a null name to sayHello just \"Hello\" should be returned")
    void provide_null_name(){
        // given
        HelloAcademy helloAcademy = new HelloAcademy();
        String name = null;
        // when
        String actualName = helloAcademy.sayHello(name);
        // then
        assertEquals("Hello", actualName, "Returned name should be just Hello when providing a null name");
    }

    @Order(2)
    @Test
    @DisplayName("When providing a empty name to sayHello just \"Hello \" should be returned")
    void provide_empty_name(){
        String name = "";
        String actualName = helloAcademy.sayHello(name);
        assertEquals("Hello ", actualName, "Returned name should be just \"Hello \" when providing a empty name");
    }

    @Order(3)
    @Test
    @DisplayName("When providing a valid name to sayHello just \"Hello {name}\" should be returned")
    void provide_valid_name(){
        String name = "Gui";
        String actualName = helloAcademy.sayHello(name);
        assertEquals("Hello Gui", actualName, "Returned name should be \"Hello Gui\" when providing a valid name");
    }

    HelloAcademy helloAcademy;
    private static final Logger log = Logger.getLogger(HelloAcademyTest.class);

    @BeforeAll
    void init(){
        helloAcademy = new HelloAcademy();
        log.info("Before all");
    }

    @AfterAll
    void cleanup(){
        log.info("After all");
    }

    @BeforeEach
    void setUp() {
        log.info("before each");
    }

    @AfterEach
    void tearDown() {
        log.info("after each");
    }

    @Order(4)
    @ParameterizedTest
    //@ValueSource(strings = {"Rennam", "João", "Maria"}) utilizado para apenas um vaor de argumento
    @MethodSource(value = "provide_valid_name1")
    @DisplayName("When providing a valid name to sayHello just \"Hello {name}\" should be returned")
    void provide_valid_name1(String name, String expected){
        // when
        String actualName = helloAcademy.sayHello(name);
        // then
        assertThat(actualName).isEqualTo(expected);
        assertAll(
                () -> assertNotNull(actualName, "Returned name should not be null when providing a name"),
                () -> assertEquals(expected, actualName, "Returned name should be \"Hello {name}\" when providing a valid name")
        );
    }
}