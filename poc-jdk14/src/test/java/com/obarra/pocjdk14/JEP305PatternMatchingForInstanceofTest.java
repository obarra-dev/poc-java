package com.obarra.pocjdk14;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JEP305PatternMatchingForInstanceofTest {

    /**
     * JEP305 is a Preview
     */
    @Test
    void instanceofWithPatternMatching() {
        Object number = BigDecimal.TEN;

        if (number instanceof BigDecimal bigDecimal){
            System.out.println(bigDecimal);
            assertEquals(BigDecimal.class, bigDecimal.getClass());
        } else {
            fail();
        }
    }

    /**
     * here the Pattern matching is not necessary.
     */
    @Test
    void instanceofWithPatternMatchingNegate() {
        Object number = "this a string";
        if (!(number instanceof BigDecimal bigDecimal)){
            assertEquals(String.class, number.getClass());
        }
    }

    @Test
    void instanceofTraditional() {
        Object number = BigDecimal.TEN;
        if (number instanceof BigDecimal) {
            BigDecimal bigDecimal = (BigDecimal) number;
            System.out.println(bigDecimal);
            assertEquals(BigDecimal.class, bigDecimal.getClass());
        } else {
            fail();
        }
    }
}
