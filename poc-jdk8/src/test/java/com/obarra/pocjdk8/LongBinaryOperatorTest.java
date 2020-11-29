package com.obarra.pocjdk8;

import org.junit.jupiter.api.Test;

import java.util.function.LongBinaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongBinaryOperatorTest {

    @Test
    public void applyAsLong() {
        LongBinaryOperator getMinor = (x, y) -> x > y ? y : x;
        assertEquals(-2, getMinor.applyAsLong(-2, 3));
        assertEquals(5, getMinor.applyAsLong(10, 5));
    }
}
