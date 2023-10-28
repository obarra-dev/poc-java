package com.obarra.pocjdk8;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StreamTest {

    @Test
    public void sortedAndReduceWhenStreamWasClosed() {
        Stream<String> strings = Stream.of("a", "c", "b", "e");
        strings.sorted();
        assertThrows(IllegalStateException.class, () -> strings.reduce("", String::concat));
    }

    @Test
    public void sortedAndReduce() {
        String strings = Stream.of("a", "c", "b", "e")
                .sorted()
                .reduce("", String::concat);

        assertEquals("abce", strings);
    }

    @Test
    public void sortedAndForEach() {
        StringBuilder stringBuilder = new StringBuilder();
        Stream.of("a", "c", "b", "e")
                .sorted()
                .forEach(stringBuilder::append);

        assertEquals("abce", stringBuilder.toString());
    }

    @Test
    public void mapWhenHasNestedStructure() {
        List<List<String>> list = Arrays.asList(Arrays.asList("a"),
                Arrays.asList("b"),
                Arrays.asList("c"));

        List<Stream<String>> nestedStructure = list.stream()
                .map(Collection::stream)
                .collect(Collectors.toList());

        List<String> noNestedStructure = nestedStructure.stream()
                .map(s -> s.findAny().get())
                .collect(Collectors.toList());

        String[] expected = {"a", "b", "c"};
        assertArrayEquals(expected, noNestedStructure.toArray());
    }


    @Test
    public void flatMapWhenHasNestedStructure() {
        List<List<String>> list = Arrays.asList(Arrays.asList("a"),
                Arrays.asList("b"),
                Arrays.asList("c"));

        List<String> noNestedStructure = list.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        String[] expected = {"a", "b", "c"};
        assertArrayEquals(expected, noNestedStructure.toArray());
    }

    @Test
    public void flatMapWhenHasNestedStructureUsingCustomMappingFunction() {
        List<List<String>> list = Arrays.asList(Arrays.asList("a"),
                Arrays.asList("b"),
                Arrays.asList("c"));

        List<String> noNestedStructure = list.stream()
                .flatMap(l -> l.stream())
                .collect(Collectors.toList());

        String[] expected = {"a", "b", "c"};
        assertArrayEquals(expected, noNestedStructure.toArray());
    }

    @Test
    public void flatMapUsingCustomMappingFunction() {
        List<String> list = Arrays.asList("a", "b", "c");

        List<String> collect = list.stream()
                .flatMap(e -> Stream.of(e.toLowerCase(), e.toUpperCase()))
                .collect(Collectors.toList());

        String[] expected = {"a", "A", "b", "B", "c", "C"};
        assertArrayEquals(expected, collect.toArray());
    }

    @Test
    public void flatMapWhenIsLikeAMap() {
        List<String> list = Arrays.asList("a", "b", "c");

        // in this case the flapMap acts as a map but it is creating a Stream which has overhead
        List<String> collect = list.stream()
                .flatMap(e -> Stream.of(e.toUpperCase()))
                .collect(Collectors.toList());

        String[] expected = {"A", "B", "C"};
        assertArrayEquals(expected, collect.toArray());
    }


    /**
     * Se ejecuto de forma secuencial porque el origen del Stream es List el cual es por definicion ordenada
     * El collecto deberia tener como minimo la caracteristica UNORDENRED
     * Al no tener esta caractica indica que el resultado del joinig depende del orden de los elementos
     * por lo tanto no se puede ejecutar de forma paralela
     */
    @Test
    public void parallelStream() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        String joined = list.parallelStream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        assertEquals("1,2,3,4,5,6,7,8,9", joined);
    }
}
