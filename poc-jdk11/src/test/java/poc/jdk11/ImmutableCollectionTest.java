package poc.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// since java 9
// static factory methods on the List, Set, and Map interfaces let you easily create unmodifiable lists, sets, and maps
// avoid  side effects
class ImmutableCollectionTest {

    @Test
    void listOf() {
        var immutable = List.of("Java", "Kotlin");

        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            immutable.add("Golang");
        });
        Assertions.assertEquals("java.util.ImmutableCollections$List12", immutable.getClass().getName());
    }

    @Test
    void setOf() {
        var immutable = Set.of("Java", "Kotlin");

        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            immutable.add("Golang");
        });
        Assertions.assertEquals("java.util.ImmutableCollections$Set12", immutable.getClass().getName());
    }

    // TODO use with Map.Entry
    @Test
    void mapOf() {
        var immutable = Map.of("Java", "cool", "Kotlin", "nice");

        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            immutable.put("Golang", "a good option");
        });
        Assertions.assertEquals("java.util.ImmutableCollections$MapN", immutable.getClass().getName());
    }

}
