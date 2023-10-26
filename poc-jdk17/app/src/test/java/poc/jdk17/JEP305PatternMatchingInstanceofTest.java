package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// since java 14
// JEP305, JEP375, JEP394
class JEP305PatternMatchingInstanceofTest {

    @Test
    void patternMatchingMoreCases() {
        assertEquals("1", getStringValue(BigDecimal.ONE));
        assertEquals("12343242", getStringValue("12343242"));
        assertEquals("123", getStringValue(123));
        assertEquals("-999999999-01-01", getStringValue(LocalDate.MIN));
        assertEquals("unknown", getStringValue(Double.MIN_VALUE));

        // notice it does not throw null pointer
        assertEquals("unknown", getStringValue(null));
    }

    private String getStringValue(Object anyValue) {
        String result;
        if (anyValue instanceof String str) {
            result = str;
        } else if (anyValue instanceof BigDecimal bd) {
            result = bd.toEngineeringString();
        } else if (anyValue instanceof Integer i) {
            result = Integer.toString(i);
        } else if (anyValue instanceof LocalDate ld) {
            result = ld.format(DateTimeFormatter.ISO_LOCAL_DATE);
        } else {
            result = "unknown";
        }

        return result;
    }

    @Test
    void patternMatchingCombiningWithConditional() {
        Object obj = "Golang";
        // the compiler detects it is a string, now we can use its methods
        // combining the new binding variable with conditional statements
        if (obj instanceof String s && s.length() > 4) {
            int length = s.length();
            Assertions.assertEquals(6, length);
        } else {
            fail();
        }
    }


    @Test
    void instanceofWithRecord() {
        Object object = new PersonRecord("omar", 12);

        if (object instanceof PersonRecord personRecord) {
            // the compiler detects it is a PersonRecord, now we can use its methods
            assertEquals(new PersonRecord("omar", 12), personRecord);
            assertEquals("omar", personRecord.firstName());
            assertEquals(PersonRecord.class, personRecord.getClass());
        } else {
            fail();
        }
    }

    @Test
    void patternMatchingString() {
        Object obj = "Golang";
        if (obj instanceof String s) {
            // the compiler detects it is a string, now we can use its methods
            int length = s.length();
            Assertions.assertEquals(6, length);
        } else {
            fail();
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
