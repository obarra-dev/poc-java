package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
