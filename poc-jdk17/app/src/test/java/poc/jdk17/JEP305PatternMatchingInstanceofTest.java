package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// JEP305
class JEP305PatternMatchingInstanceofTest {

    // since java 14
    @Test
    void patternMatching() {
        Object obj = "Golang";
        if (obj instanceof String s) {
            int length = s.length();
            Assertions.assertEquals(6, length);
        }
    }

    @Test
    void instanceofWithPatternMatching() {
        Object number = BigDecimal.TEN;

        if (number instanceof BigDecimal bigDecimal) {
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
