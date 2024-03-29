package poc.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class OptionalTest {

    @Test
    void isEmpty() {
        var option = Optional.ofNullable(null);
        Assertions.assertTrue(option.isEmpty());

        option = Optional.ofNullable("omar");
        Assertions.assertFalse(option.isEmpty());

        option = Optional.ofNullable("");
        Assertions.assertFalse(option.isEmpty());

        option = Optional.ofNullable("   ");
        Assertions.assertFalse(option.isEmpty());

        // is preset is older, but empty is more easy to read
        option = Optional.ofNullable("   ");
        Assertions.assertTrue(option.isPresent());
    }


    // since java 10
    // It’s synonymous with and is now the preferred alternative to the existing get() method.
    @Test
    void orElseThrow() {
        var option = Optional.ofNullable(null);

        // now it is clearer
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            Object o = option.orElseThrow();
        });

        // before
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            Object o = option.get();
        });

        var option2 = Optional.ofNullable("somevalue");

        Object o = option2.orElseThrow();
        Assertions.assertNotNull(o);

        o = option2.get();
        Assertions.assertNotNull(o);
    }

    // since java 10
    @Test
    void orElseThrowOtherExample() {
        Integer result = Stream.of(1, 2, 3)
                .findFirst()
                .orElseThrow();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result);
    }

    // since java 9
    @Test
    void orShouldReturnOtherOptional() {
        var option = Optional.ofNullable(null);
        var otherOptional = option.or(() -> Optional.of("otherOptional"));

        Assertions.assertNotNull(otherOptional.get());
    }

    // since java 9
    @Test
    void ifPresentOrElse() {
        var option = Optional.ofNullable(null);
        option.ifPresentOrElse(System.out::println, () -> System.out.println("no data"));
    }

    // since java 9
    @Test
    void optionalToStream() {
        var option = Optional.ofNullable("something");
        var result = option.stream().collect(Collectors.toList());
        Assertions.assertEquals(List.of("something"), result);
    }
}
