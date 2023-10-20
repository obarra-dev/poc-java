package poc.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// since java 10
class UnmodifiableTest {

    // TODO use for set and map
    @Test
    void unmodifiableList() {
        var list = new ArrayList<String>();
        list.add("Java");
        list.add("Kotlin");

        var unmodifiable = Collections.unmodifiableList(list);

        Assertions.assertEquals("java.util.ArrayList", list.getClass().getName());
        Assertions.assertEquals("java.util.Collections$UnmodifiableRandomAccessList", unmodifiable.getClass().getName());
        Assertions.assertEquals(list, unmodifiable);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            unmodifiable.add("Golang");
        });
    }

    @Test
    void unmodifiableListButMutable() {
        var list = new ArrayList<String>();
        list.add("Java");
        list.add("Kotlin");

        // it is unmodifiable but mutable  if we use the original list!!
        var unmodifiableMutable = Collections.unmodifiableList(list);

        // some work around
        var unmodifiable = Collections.unmodifiableList(new ArrayList<>(list));

        list.add("Golang");

        Assertions.assertEquals("java.util.Collections$UnmodifiableRandomAccessList", unmodifiableMutable.getClass().getName());
        Assertions.assertEquals("java.util.Collections$UnmodifiableRandomAccessList", unmodifiable.getClass().getName());

        Assertions.assertEquals(list, unmodifiableMutable);
        Assertions.assertNotEquals(list, unmodifiable);
        Assertions.assertNotEquals(unmodifiableMutable, unmodifiable);
    }

    @Test
    void immutableCopyOf() {
        var list = new ArrayList<String>();
        list.add("Java");
        list.add("Kotlin");

        // some work around
        var immutable = List.copyOf(list);

        list.add("Golang");

        Assertions.assertEquals("java.util.ImmutableCollections$List12", immutable.getClass().getName());
        Assertions.assertNotEquals(list, immutable);
    }


    @Test
    void toUnmodifiableList() {
        var list = new ArrayList<String>();
        list.add("Java");
        list.add("Kotlin");

        var unmodifiable = list.stream()
                .filter(Predicate.isEqual("Java"))
                .collect(Collectors.toUnmodifiableList());

        Assertions.assertEquals("java.util.ArrayList", list.getClass().getName());
        Assertions.assertEquals("java.util.ImmutableCollections$List12", unmodifiable.getClass().getName());
        Assertions.assertEquals(List.of("Java"), unmodifiable);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            unmodifiable.add("Golang");
        });
    }

    // toUnmodifiableList seems to be immutable
    @Test
    void toUnmodifiableListAndImmutable() {
        var list = new ArrayList<String>();
        list.add("Java");
        list.add("Kotlin");

        var unmodifiableAndImmutable = list.stream()
                .filter(Predicate.isEqual("Java"))
                .collect(Collectors.toUnmodifiableList());

        list.add("Golang");
        list.remove("Kotlin");
        Assertions.assertEquals("java.util.ImmutableCollections$List12", unmodifiableAndImmutable.getClass().getName());
        Assertions.assertNotEquals(list, unmodifiableAndImmutable);
    }
}
