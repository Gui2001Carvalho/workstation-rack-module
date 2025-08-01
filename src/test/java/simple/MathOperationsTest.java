package simple;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MathOperationsTest {

    public static Stream<Arguments> provide_valid_sum() {
        return Stream.of(
                Arguments.of(1, 1, 2),
                Arguments.of(-2, -2, -4),
                Arguments.of(1, 2, 3)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "provide_valid_sum")
    void provide_valid_sum(int a, int b, int expected){
        int result = a + b;
        assertEquals(expected, result);
    }

    public static Stream<Arguments> provide_invalid_sum() {
        return Stream.of(
                Arguments.of(1, 1, 3),
                Arguments.of(-2, -2, 4),
                Arguments.of(0, 2, 0)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "provide_invalid_sum")
    void provide_invalid_sum(int a, int b, int expected){
        int result = a + b;
        assertNotEquals(expected, result);
    }

    public static Stream<Arguments> provide_valid_divide() {
        return Stream.of(
                Arguments.of(5, 1, 5),
                Arguments.of(4, -2, -2),
                Arguments.of(10, 2, 5)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "provide_valid_divide")
    void provide_valid_divide(int a, int b, int expected){
        int result = a / b;
        assertEquals(expected, result);
    }

    public static Stream<Arguments> provide_invalid_divide() {
        return Stream.of(
                Arguments.of(10, 1, 1),
                Arguments.of(4, -2, 2),
                Arguments.of(6, 2, 2)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "provide_invalid_divide")
    void provide_invalid_divide(int a, int b, int expected){
        int result = a / b;
        assertNotEquals(expected, result);
    }

    @Test
    void provide_exception_invalid_divide() {
        assertThrows(ArithmeticException.class, () -> {
            int result = 5/0;
        });
    }
}