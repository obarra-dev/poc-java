package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

//  Java has made this easier by adding the capability to point out what exactly was null in a given line of code.
// since java 14
// JEP358
class JEP358HelpfulNullPointerExceptionsTest {
    @Test
    void helpfulNullPointerExceptionTest() {
        // now you can see this log
        // Cannot invoke "java.math.BigDecimal.add(java.math.BigDecimal)" because "myVariable" is null
        Assertions.assertThrows(NullPointerException.class, () -> {
            bigDecimalAdd();
        });
    }

    private void bigDecimalAdd() throws NullPointerException {
        try {
            BigDecimal myVariable = null;
            myVariable.add(BigDecimal.TEN);
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    @Test
    void helpfulNullPointerExceptionMapPutGet() {
        // now you can see this log
        // Cannot invoke "java.math.BigDecimal.add(java.math.BigDecimal)" because the return value of "java.util.Map.get(Object)" is null
        Assertions.assertThrows(NullPointerException.class, () -> {
            mapPutGet();
        });
    }

    private void mapPutGet() {
        try {
            BigDecimal myVariable = null;
            Map<String, BigDecimal> map = new HashMap<>();
            map.put("first", myVariable);
            map.get("first").add(BigDecimal.TEN);
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new NullPointerException();
        }

    }
}
