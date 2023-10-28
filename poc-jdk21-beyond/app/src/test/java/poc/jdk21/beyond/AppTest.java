package poc.jdk21.beyond;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void stringTemplate() {

        int a = 2;
        int b = 3;
        String interpolated = STR."\{a} times \{b} = \{a * b}";

        Assertions.assertEquals("2 times 3 = 6", interpolated);
    }
}
