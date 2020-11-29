package poc.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

class StringTest {

    @Test
    void isBlank() {
        Assertions.assertTrue("   ".isBlank());
    }

    @Test
    void isBlankWhenStringIsNullShouldThrowsNullPointerException() {
        String s = null;
        Assertions.assertThrows(NullPointerException.class, () -> {
            s.isBlank();
        });
    }

    @Test
    void lines() {
        var result = "omar\nbarra\njava".lines().collect(Collectors.toList());
        var expected = List.of("omar", "barra", "java");
        Assertions.assertEquals(expected, result);
    }


    // strip is unicode-aware evolution of trim
    @Test
    void strip() {
        var s = "\n\t omar barra \u2005 ";
        Assertions.assertEquals("omar barra", s.strip());
    }

    @Test
    void stripLeading() {
        var s = "\n\t omar barra \u2005 ";
        Assertions.assertEquals("omar barra \u2005 ", s.stripLeading());
    }

    @Test
    void stripTrailing() {
        var s = "\n\t omar barra \u2005 ";
        Assertions.assertEquals("\n\t omar barra", s.stripTrailing());
    }

    @Test
    void stripU00a0() {
        var s = "\n\t omar barra \u00a0 ";
        Assertions.assertEquals("omar barra \u00a0", s.strip());
    }

    @Test
    void repeat() {
        var s = "test";
        Assertions.assertEquals("testtesttesttesttest", s.repeat(5));
    }

    @Test
    void repeatWhenRepeatIsNegativeShouldBeThrowsIllegalArgumentException() {
        var s = "test";
        Assertions.assertThrows(IllegalArgumentException.class, () -> s.repeat(-1));
    }
}
