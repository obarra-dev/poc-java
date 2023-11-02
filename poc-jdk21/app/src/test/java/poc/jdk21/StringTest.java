package poc.jdk21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class StringTest {

    // Splitting a Java String by Multiple Delimiters and return its delimiters
    @Test
    void splitWithDelimitersReturnAlsoTheDelimitersUsingMultipleDelimiters() {
        String string = "Mary;Thomas:Jane-Kate";
        String[] result = string.splitWithDelimiters("[;:-]", -1);
        String[] expected = {"Mary", ";", "Thomas", ":", "Jane", "-", "Kate"};

        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    void splitWithDelimitersReturnAlsoTheDelimitersUsingOneDelimiter() {
        String string = "welcome to the jungle";
        String[] result = string.splitWithDelimiters(" ", 2);
        String[] expected = {"welcome", " ", "to the jungle"};

        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    void indexOfWithRange() {
        String string = "Omar Omar Barra Barra";
        int result = string.indexOf("Omar", 5, 9);
        Assertions.assertEquals(5, result);
    }

    @Test
    void indexOfWithRangeChar() {
        String string = "Omar Omar Barra Barra";
        int result = string.indexOf('O', 5, 6);
        Assertions.assertEquals(5, result);
    }
}
