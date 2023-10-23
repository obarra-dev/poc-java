package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class RandomGeneratorFactoryTest {

    @Test
    void newRandomGenerator() {
        IntStream l128X256MixRandom = RandomGeneratorFactory.of("L128X256MixRandom")
                .create()
                .ints(10, 0, 100);

        int[] ints = l128X256MixRandom.toArray();

        Assertions.assertEquals(10, ints.length);
    }

}
