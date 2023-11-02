package poc.jdk25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.util.FormatProcessor.FMT;

class JEP443UnnamedPatternsAndVariablesTest {


    @Test
    void mm() {

        try {
            int number = Integer.parseInt("string");
        } catch (NumberFormatException _) {
            System.err.println("Not a number");
            return;
        }

        Assertions.fail();

    }

}
