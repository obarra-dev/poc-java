package com.obarra.pocjdk14;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JEP305PatternMatchingForInstanceofTest {

    /**
     * JEP305 is a Preview
     */
    @Test
    void switchExpressions() {
        Object number = BigDecimal.TEN;

        if (number instanceof BigDecimal bigDecimal) {
            System.out.println(bigDecimal);
            assertEquals(BigDecimal.class, bigDecimal.getClass());
        }

    }

    @Test
    void switchExpressionsTraditional() {
        Object number = BigDecimal.TEN;
        if (number instanceof BigDecimal ) {
            BigDecimal bigDecimal = (BigDecimal) number;
            System.out.println(bigDecimal);
            assertEquals(BigDecimal.class, bigDecimal.getClass());
        }

    }
}
