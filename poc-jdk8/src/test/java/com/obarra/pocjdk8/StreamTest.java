package com.obarra.pocjdk8;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StreamTest {

    @Test
    public void testt() {
        final SortedSet<PersonDTO> deduped = new TreeSet<>(new Comparator<PersonDTO>() {
            @Override
            public int compare(final PersonDTO o1, final PersonDTO o2) {
                int result = o1.firstName.compareTo(o2.firstName);
                if (result != 0) {
                    return result;
                }

                result = o1.lastName.compareTo(o2.lastName);
                if (result != 0) {
                    return result;
                }

                return 8;
            }
        });

        PersonDTO personDTO = new PersonDTO();
        personDTO.firstName = "omar";
        personDTO.lastName = "barra";
        deduped.add(personDTO);

        personDTO = new PersonDTO();
        personDTO.firstName = "omar";
        personDTO.lastName = "barra";
        deduped.add(personDTO);

        deduped.forEach(s  -> System.out.println(s.firstName + " " + s.lastName));

    }

    @Test
    public void streamBuilderUsingAccept() {
        Stream.Builder<String> builder = Stream.builder();
        builder.accept("omar");
        builder.accept("barra");
        builder.accept("java");
        builder.accept("golang");

        Stream<String> stream = builder.build();

        List<String> list = stream.map(String::toUpperCase)
            .collect(Collectors.toList());

        String[] expected = {"OMAR", "BARRA", "JAVA", "GOLANG"};
        assertArrayEquals(expected, list.toArray());
    }


    @Test
    public void streamBuilderUsingAdd() {
        Stream.Builder<String> builder = Stream.builder();
        Stream<String> stream = builder.add("omar")
            .add("barra")
            .add("java")
            .add("golang")
            .build();

        List<String> list = stream.map(String::toUpperCase)
            .collect(Collectors.toList());

        String[] expected = {"OMAR", "BARRA", "JAVA", "GOLANG"};
        assertArrayEquals(expected, list.toArray());
    }

    @Test
    public void dsdsss() {
        Iterable<Integer> d = () -> Stream.of(4).iterator();


    }

    @Test
    public void dsds() {
        walkHierarchy().forEach(System.out::println);
    }

    public Iterable<Integer> walkHierarchy() {
        // We use Stream for its laziness,
        // but Stream itself cannot be iterated multiple times, so we must wrap its
        // construction in this Iterable that,
        // if its iterator() method were called multiple times, would repeat this
        // logic

        return () -> {

            // Because the returned list will contain objects of multiple different types,
            // we have to fetch them in multiple
            // JPA queries. Note that due to the limitations of TransactionContext (it is not thread-safe) we cannot do these
            // queries concurrently

            Stream.Builder<Stream<Integer>> hierarchyBuilder = Stream.builder();

                Supplier<Integer> repoManagerSupplier = () -> 4;

                hierarchyBuilder.accept(
                    lazy(repoManagerSupplier)
                        .filter(Objects::nonNull)
                        // NOTE: we avoid doing a separate database lookup for the RepositoryContainer - we know that if
                        // a repo manager is an ancestor then a RepositoryContainer is as well.
                        .flatMap(repoManager -> Stream.of(repoManager, 5))
                );

                hierarchyBuilder.accept(Stream.of(9));

            return hierarchyBuilder.build()
                .flatMap(Function.identity())
                .filter(Objects::nonNull)
                .iterator();
        };
    }

    private static <T> Stream<T> lazy(Supplier<T> supplier) {
        return Stream.of(supplier).map(Supplier::get);
    }

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


    @Test
    public void parallelStreamShowThreadNamesInForEach() {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        listOfNumbers.parallelStream().forEach(number ->
            System.out.println("Value: " + number + " Thread: " + Thread.currentThread().getName())
        );
    }

    @Test
    public void parallelStreamShowThreadNamesInCollect() {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        Map<Integer, Integer> collect = listOfNumbers.parallelStream()
            .collect(Collectors.toMap(x -> x, x -> {
                System.out.println("Value: " + x + " Thread: " + Thread.currentThread().getName());
                return x * 2;
            } ));

        collect.forEach((x, y) ->  System.out.println(y));
    }

    @Test
    public void parallelStreamCustom() {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        Map<Integer, Integer> collect = StreamSupport.stream(listOfNumbers.spliterator(), true /* parallel */)
            .flatMap(x -> {
                System.out.println("IN FlatMat Value: " + x + " Thread: " + Thread.currentThread().getName());

                return Stream.of(x, listOfNumbers.size() + x * 2);
            })
            .collect(Collectors.toMap(x -> x, x -> {
                System.out.println("IN Collector Value: " + x + " Thread: " + Thread.currentThread().getName());
                return x * 2;
            }));

        collect.forEach((x, y) ->  System.out.println(y));
    }

    @Test
    public void parallelStreamWithDecorator() {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        Map<Integer, Integer> collect = listOfNumbers.parallelStream()
            .flatMap(new DecoratorFunction<>(x -> {
                System.out.println("IN FlatMat Value: " + x + " Thread: " + Thread.currentThread().getName());

                return Stream.of(x, listOfNumbers.size() + x * 2);
            }))
            .collect(Collectors.toMap(x -> x, x -> {
                System.out.println("IN Collector Value: " + x + " Thread: " + Thread.currentThread().getName());
                return x * 2;
            }));

        collect.forEach((x, y) ->  System.out.println(y));
    }

    @Test
    public void parallelStreamWithCustomForkJoin() throws ExecutionException, InterruptedException {
        long firstNum = 1;
        long lastNum = 1_000_000;

        List<Long> aList = LongStream.rangeClosed(firstNum, lastNum).boxed()
            .collect(Collectors.toList());

        ForkJoinPool customThreadPool = new ForkJoinPool(4);
        long result = customThreadPool
            .submit(() -> aList.parallelStream().reduce(0L, Long::sum))
            .get();

        assertEquals(500000500000L, result);
    }

    @Test
    public void parallelStreamForSum() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        Integer result = list.parallelStream().reduce(0, Integer::sum);

        assertEquals(15, result);
    }

    @Test
    public void parallelStreamCustomd() {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        Map<Integer, Integer> collect = StreamSupport.stream(listOfNumbers.spliterator(), true /* parallel */)
            .collect(Collectors.toMap(new DecoratorFunction<>(x -> x), new DecoratorFunction<>(x -> {
                System.out.println("IN Collector Value: " + x + " Thread: " + Thread.currentThread().getName());
                return x * 2;
            })));

        collect.forEach((x, y) ->  System.out.println(y));
    }

    @Test
    public void parallelStreamCustomsd() {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        List<Integer> collect = CompletableFuture.supplyAsync(() -> listOfNumbers.parallelStream()
                .map(new DecoratorFunction<>(
                    x ->  x * 2))
                .collect(toList()),
            new ForkJoinPool(4)).join();

        collect.forEach(x ->  System.out.println(x));

    }
}
