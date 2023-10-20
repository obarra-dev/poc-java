package poc.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

// since java 10
class JEP286VarTest {

    // Local Variable Type Inference
    @Test
    void varExample() {
        var list = List.of("Java", "Kotlin", " ");
        // list = "somestring"; does not compile
        List<String> listExpected = List.of("Java", "Kotlin", " ");
        Assertions.assertEquals(listExpected, list);
        Assertions.assertEquals("java.util.ImmutableCollections$ListN", list.getClass().getName());

        var string = "hello";
        String stringExpected = "hello";
        Assertions.assertEquals(stringExpected, string);
        Assertions.assertEquals("java.lang.String", string.getClass().getName());

        var number = 1.4;
        double numberExpected = 1.4;
        Assertions.assertEquals(numberExpected, number);
        // note java does not create a Double, instead create primitive double
        Assertions.assertEquals("java.lang.Double", Double.valueOf(number).getClass().getName());

    }
}
