package com.obarra.pocjdk8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * In JDK8 exist the concepts of effective final variable, which allows
 * a non-final variable to be accessed inside an inner class or lambda expression.
 * But We can not change this effective final. Inside this is a final.
 */
public class EffectivelyFinalTest {

    @Test
    void effectiveFinalInMap() {
        List<String> names = Arrays.asList("omar", "barra");
        String lastName = "maru";
        List<String> result = names.stream()
                .map(x -> lastName)
                .collect(Collectors.toList());

        List<String> expected = Arrays.asList("maru", "maru");
        Assertions.assertIterableEquals(expected, result);
    }

    @Test
    void effectiveFinalInRunnableLambdaExpression() throws InterruptedException {
        AtomicReference<String> effectiveFinal = new AtomicReference<>("I am non final local variable");
        Runnable runnable = () -> {
            System.out.println(effectiveFinal.get());
            effectiveFinal.set("ValueChanged");
        };

        Thread thread = new Thread(runnable);
        thread.start();

        thread.join();
        Assertions.assertEquals("ValueChanged", effectiveFinal.get());
    }

    @Test
    void effectiveFinalInRunnableInnerAnonymousClass() throws InterruptedException {
        AtomicReference<String> effectiveFinal = new AtomicReference<>("I am non final local variable");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(effectiveFinal.get());
                effectiveFinal.set("ValueChanged");
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        thread.join();
        Assertions.assertEquals("ValueChanged", effectiveFinal.get());
    }

}
