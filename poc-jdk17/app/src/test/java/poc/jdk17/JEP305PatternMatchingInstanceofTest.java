package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JEP305PatternMatchingInstanceofTest {
    @Test
    void transform() {
        Object obj = "Golang";
        if (obj instanceof String s) {
            int length = s.length();
            Assertions.assertEquals(6, length);
        }
    }
}
