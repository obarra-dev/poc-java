package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Locale;

class NumberFormatTest {
    @Test
    public void givenNumber_thenCompactValues() {
        NumberFormat likesShort =
                NumberFormat.getCompactNumberInstance(new Locale("en", "US"), NumberFormat.Style.SHORT);
        likesShort.setMaximumFractionDigits(2);
        Assertions.assertEquals("2.59K", likesShort.format(2592));

        NumberFormat likesLong =
                NumberFormat.getCompactNumberInstance(new Locale("en", "US"), NumberFormat.Style.LONG);
        likesLong.setMaximumFractionDigits(2);
        Assertions.assertEquals("2.59 thousand", likesLong.format(2592));
    }
}
