package com.obarra.pocjdk8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * In JDK8 exist the concepts of effective final variable, which allows
 * a non-final variable to be accessed inside an inner class or lambda expression.
 * But We can not change this effective final. Inside this is a final.
 */
public class EffectivelyFinalTest {

    @Test
    void effectiveFinalInMapLambdaExpression() {
        List<String> names = Arrays.asList("omar", "barra");
        // lastName is effectively final here
        String lastName = "maru";

        // if I do it lastName lost its effectively final and ".map(x -> lastName)" does not compile
        // lastName = "other name";
        List<String> result = names.stream()
                .map(x -> lastName)
                .collect(Collectors.toList());

        List<String> expected = Arrays.asList("maru", "maru");
        Assertions.assertIterableEquals(expected, result);
    }

    @Test
    void effectiveFinalInAnonymousClass() throws InterruptedException {
        // name is effectively final here
        String name = "omar";
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(name);

                // if I do it name lost its effectively final
                //name = "sdfs";
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        thread.join();
        Assertions.assertEquals("omar", name);
    }

    // It’s not a good practice to modify variables used in lambda expressions and anonymous classes.
    // But there’s an alternative approach that allows us to modify variables in such cases that achieves thread-safety through atomicity.
    // AtomicReference and AtomicInteger
    @Test
    void atomicReferenceInRunnableLambdaExpression() throws InterruptedException {
        //  We can use AtomicReference to atomically modify variables inside lambda expressions
        AtomicReference<String> modifiableVariable = new AtomicReference<>("I am non final local variable");
        Runnable runnable = () -> {
            System.out.println(modifiableVariable.get());
            modifiableVariable.set("ValueChanged");
        };

        Thread thread = new Thread(runnable);
        thread.start();

        thread.join();
        Assertions.assertEquals("ValueChanged", modifiableVariable.get());
    }

    @Test
    void atomicReferenceInRunnableInnerAnonymousClass() throws InterruptedException {
        //  We can use AtomicReference to atomically modify variables inside InnerAnonymousClass
        AtomicReference<String> modifiableVariable = new AtomicReference<>("I am non final local variable");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(modifiableVariable.get());
                modifiableVariable.set("ValueChanged");
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        thread.join();
        Assertions.assertEquals("ValueChanged", modifiableVariable.get());
    }

    @Test
    void atomicIntegerInRunnableLambdaExpression() throws InterruptedException {
        //  We can use AtomicReference to atomically modify variables inside lambda expressions
        AtomicInteger modifiableVariable = new AtomicInteger(10);
        Runnable runnable = () -> {
            System.out.println(modifiableVariable.get());
            modifiableVariable.set(11);
        };

        Thread thread = new Thread(runnable);
        thread.start();

        thread.join();
        Assertions.assertEquals(11, modifiableVariable.get());
    }
}
