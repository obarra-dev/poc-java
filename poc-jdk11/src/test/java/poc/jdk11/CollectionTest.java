package poc.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class CollectionTest {

    @Test
    void toArrayUseIntFunction() {
        var result = List.of("apple", "banana", "orange").toArray(String[]::new);
        String[] expected = {"apple", "banana", "orange"};
        Assertions.assertArrayEquals(expected, result);
    }

}
