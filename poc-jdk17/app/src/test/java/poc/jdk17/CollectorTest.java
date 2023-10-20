package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CollectorTest {
    @Test
    void teeing() {
        double average = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.teeing(Collectors.summingDouble(i -> i),
                        Collectors.counting(),
                        (sum, count) -> sum / count));

        Assertions.assertEquals(3.0, average);

        // Filter Names in Two Different Lists
        var result =
                Stream.of("JVM Java", "JVM Kotlin", "Golang One", "Rust One",
                                "Javascript")
                        .collect(Collectors.teeing(
                                Collectors.filtering(n -> n.contains("JVM"), Collectors.toList()),
                                Collectors.filtering(n -> n.endsWith("One"), Collectors.toList()),
                                List::of
                        ));

        Assertions.assertEquals(List.of(List.of("JVM Java", "JVM Kotlin"), List.of("Golang One", "Rust One")), result);
    }
}
