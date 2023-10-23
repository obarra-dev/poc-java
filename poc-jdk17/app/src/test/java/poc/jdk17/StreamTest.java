package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StreamTest {

    // since java 16
    // Stream.mapMulti method is similar to Stream::flatMap
    // flatMap is to merge collections contained in a stream into a single collection
    // it’s recommended to use mapMulti over flatMap
    // when a few stream elements need to be replaced
    // or when it’s easier to use an imperative approach to generate the elements of the stream pipeline.
    @Test
    void mapMulti() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        double percentage = .01;
        List<Double> evenDoubles = integers.stream()
                .<Double>mapMulti((integer, consumer) -> {
                    if (integer % 2 == 0) {
                        consumer.accept((double) integer * (1 + percentage));
                    }
                })
                .toList();
        Assertions.assertEquals(List.of(2.02, 4.04), evenDoubles);
    }

}
