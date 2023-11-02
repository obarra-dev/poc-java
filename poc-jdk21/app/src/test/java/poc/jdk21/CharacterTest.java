package poc.jdk21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharacterTest {
    @Test
    void clamp() {
        int min = 5;
        int max = 10;
        Assertions.assertEquals(6,  Math.clamp(6, min, max));
        Assertions.assertEquals(9,  Math.clamp(9, min, max));
        Assertions.assertEquals(max,  Math.clamp(11, min, max));
        Assertions.assertEquals(min,  Math.clamp(4, min, max));
        Assertions.assertEquals(max,  Math.clamp(10, min, max));
        Assertions.assertEquals(min,  Math.clamp(5, min, max));
    }

}
