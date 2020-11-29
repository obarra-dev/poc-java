package com.obarra.pocjdk8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MapTest {

    private Map<Integer, String> map1;
    private Map<Integer, String> map2;

    @BeforeEach
    void setUp() {
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        map1.put(1, "employee1");
        map1.put(2, "employee2");
        map1.put(3, "employee3");

        map2.put(4, "employee4");
        map2.put(5, "employee5");
    }

    @Test
    public void mergeWithSimpleUse() {
        BiFunction<String, String, String> biFunction =
                (v1, v2) -> "omar".equals(v2) ? v2 : v1;

        Map<Integer, String> mergedMap = new HashMap<>(map1);
        mergedMap.merge(1, "other", biFunction);
        mergedMap.merge(2, "omar", biFunction);

        Map<Integer, String> expected = new HashMap<>();
        expected.put(1, "employee1");
        expected.put(2, "omar");
        expected.put(3, "employee3");
        Assertions.assertIterableEquals(expected.entrySet(), mergedMap.entrySet());
    }

    @Test
    public void mergeIntoForEachWithOutConflict() {
        BiFunction<String, String, String> biFunction = (v1, v2) -> null;
        Map<Integer, String> mergedMap = new HashMap<>(map1);

        map2.forEach(
                (key, value) -> mergedMap
                        .merge(key, value, biFunction));

        Map<Integer, String> expected = new HashMap<>();
        expected.put(1, "employee1");
        expected.put(2, "employee2");
        expected.put(3, "employee3");
        expected.put(4, "employee4");
        expected.put(5, "employee5");
        Assertions.assertIterableEquals(expected.entrySet(), mergedMap.entrySet());
    }

    @Test
    public void forEach() {
        StringBuilder stringBuilder = new StringBuilder();
        BiConsumer<Integer, String> biConsumer = (k, v) -> stringBuilder.append(v);

        map1.forEach(biConsumer);

        Assertions.assertEquals("employee1employee2employee3", stringBuilder.toString());
    }

    @Test
    public void entryStreamForEach() {
        StringBuilder stringBuilder = new StringBuilder();
        Consumer<Map.Entry<Integer, String>> consumer = e -> stringBuilder.append(e.getValue());

        map1.entrySet()
                .stream()
                .forEach(consumer);

        Assertions.assertEquals("employee1employee2employee3", stringBuilder.toString());
    }

    @Test
    public void streamFilterCollect() {
        map1.putIfAbsent(10, "maru");
        Map<Integer, String> result = map1.entrySet()
                .stream()
                .filter(e -> "maru".equals(e.getValue()))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

        Map<Integer, String> expected = new HashMap<>();
        expected.put(10, "maru");
        Assertions.assertIterableEquals(expected.entrySet(), result.entrySet());
    }

    @Test
    public void computeIfPresent() {
        map1.computeIfPresent(2, (k, v) -> v.concat(k.toString()));
        Assertions.assertEquals("employee22", map1.get(2));
    }

    @Test
    public void putWhenFailFast() {
        Iterator<Integer> iterator = map1.keySet().iterator();
        Assertions.assertThrows(ConcurrentModificationException.class,
                () -> {
                    while (iterator.hasNext()) {
                        System.out.println(iterator.next());
                        map1.put(7, "something");
                    }
                });
    }

    @Test
    public void removeWhenFailFast() {
        Iterator<Integer> iterator = map1.keySet().iterator();
        Assertions.assertThrows(ConcurrentModificationException.class,
                () -> {
                    while (iterator.hasNext()) {
                        System.out.println(iterator.next());
                        map1.remove(2);
                    }
                });
    }

    @Test
    public void removeWhenNotFailFast() {
        Iterator<Integer> iterator = map1.keySet().iterator();
        Assertions.assertDoesNotThrow(() -> {
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
                iterator.remove();
            }
        });
    }
}
