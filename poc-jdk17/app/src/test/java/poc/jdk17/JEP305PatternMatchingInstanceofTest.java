package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// JEP305, JEP375, JEP394
class JEP305PatternMatchingInstanceofTest {

    // since java 14
    @Test
    void patternMatchingString() {
        Object obj = "Golang";
        if (obj instanceof String s) {
            // the compiler detects it is a string, now we can use its methods
            int length = s.length();
            Assertions.assertEquals(6, length);
        }
    }

    @Test
    void patternMatchingCombiningWithConditional() {
        Object obj = "Golang";
        // the compiler detects it is a string, now we can use its methods
        // combining the new binding variable with conditional statements
        if (obj instanceof String s && s.length() > 4) {
            int length = s.length();
            Assertions.assertEquals(6, length);
        }
    }


    @Test
    void patternMatchingBigDecimal() {
        Object number = BigDecimal.TEN;

        if (number instanceof BigDecimal bigDecimal) {
            // the compiler detects it is a BigDecimal, now we can use its methods
            assertEquals(new BigDecimal("11"), bigDecimal.add(BigDecimal.ONE));
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
        // negation
        if (!(number instanceof BigDecimal bigDecimal)) {
            assertEquals(String.class, number.getClass());
        }
    }

    // old version can be used
    @Test
    void instanceofOld() {
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
