package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandles;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class DateTimeFormatterTest {

    // since java 16
    @Test
    void periodOfDaySymbolB() {
        var date = LocalTime.parse("15:25:08.690791");
        var formatter = DateTimeFormatter.ofPattern("h B");
        Assertions.assertEquals(date.format(formatter), "3 in the afternoon");
    }
}
