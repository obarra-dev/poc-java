package poc.jdk21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringBuilderTest {
    @Test
    void repeat() {
        StringBuilder sb = new StringBuilder();
        sb.repeat("Hello ", 2);
        sb.repeat(0x1f600, 5);
        sb.repeat('!', 3);
        Assertions.assertEquals("Hello Hello 😀😀😀😀😀!!!", sb.toString());
    }
}
