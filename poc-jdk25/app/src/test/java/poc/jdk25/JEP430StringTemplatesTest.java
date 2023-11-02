package poc.jdk25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.FormatProcessor.FMT;

class JEP430StringTemplatesTest {

    // string interpolation
    // STR is the name of a so-called template processor
    // (more precisely: a constant of type StringTemplate.Processor automatically imported into every Java file).
    @Test
    void templateProcessorSTR() {
        int a = 2;
        int b = 3;

        String interpolated = STR."\{a} times \{b} = \{a * b}";
        Assertions.assertEquals("2 times 3 = 6", interpolated);

        interpolated = STR."\{a} times \{b} = \{Math.multiplyExact(a, b)}";
        Assertions.assertEquals("2 times 3 = 6", interpolated);


        interpolated = STR."Today's date: \{LocalDate.of(2021,11,1).format(DateTimeFormatter.ISO_LOCAL_DATE)}";

        Assertions.assertEquals("Today's date: 2021-11-01", interpolated);
        int httpStatus = 500;
        String errorMessage = "Server error";

        interpolated = STR."""
                {
                  "httpStatus": \{httpStatus},
                  "errorMessage": "\{errorMessage}"
                }""";


        Assertions.assertEquals("""
                {
                  "httpStatus": 500,
                  "errorMessage": "Server error"
                }""", interpolated);

    }

    @Test
    void templateProcessorFMT() {
        double a = 5.6777777;
        double b = 9.0777444;

        String interpolated = FMT."%.2f\{a} times %.2f\{b} = %.2f\{a * b}";

        Assertions.assertEquals("5.68 times 9.08 = 51.54", interpolated);
    }

}
