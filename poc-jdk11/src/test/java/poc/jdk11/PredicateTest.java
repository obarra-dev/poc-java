package poc.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class PredicateTest {

    @Test
    void predicateNot() {
        var sampleList = List.of("Java", "\n \n", "Kotlin", " ");
        var result = sampleList.stream()
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());
        var expected = List.of("Java", "Kotlin");
        Assertions.assertEquals(expected, result);
    }
}
