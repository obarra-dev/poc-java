package poc.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class CollectionTest {

    @Test
    void toArrayUseWithIntFunction() {
        // new default function toArray
        var collection = List.of("apple", "banana", "orange");
        var result = collection.toArray(String[]::new);
        String[] expected = {"apple", "banana", "orange"};
        Assertions.assertArrayEquals(expected, result);
    }
}
