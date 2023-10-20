package poc.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// since java 9
class ImmutableCollectionTest {

    @Test
    void listOf() {
        var immutable = List.of("Java", "Kotlin");

        Assertions.assertEquals("java.util.ImmutableCollections$List12", immutable.getClass().getName());
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            immutable.add("Golang");
        });
    }

    @Test
    void setOf() {
        var immutable = Set.of("Java", "Kotlin");

        Assertions.assertEquals("java.util.ImmutableCollections$Set12", immutable.getClass().getName());
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            immutable.add("Golang");
        });
    }

    // TODO use with Map.Entry
    @Test
    void mapOf() {
        var immutable = Map.of("Java", "cool", "Kotlin", "nice");

        Assertions.assertEquals("java.util.ImmutableCollections$MapN", immutable.getClass().getName());
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            immutable.put("Golang", "best");
        });
    }

}
