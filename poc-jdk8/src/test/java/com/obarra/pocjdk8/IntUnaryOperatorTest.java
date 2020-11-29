package com.obarra.pocjdk8;

import org.junit.jupiter.api.Test;

import java.util.function.IntUnaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntUnaryOperatorTest {

    @Test
    public void applyAsInt() {
        IntUnaryOperator squareRoot = (x) -> x * x;
        assertEquals(0, squareRoot.applyAsInt(0));
        assertEquals(1, squareRoot.applyAsInt(1));
        assertEquals(4, squareRoot.applyAsInt(2));
        assertEquals(9, squareRoot.applyAsInt(3));
    }
}
