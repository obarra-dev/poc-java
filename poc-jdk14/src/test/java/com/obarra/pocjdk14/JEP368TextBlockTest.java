package com.obarra.pocjdk14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Record is a compact & concise way for declaring data classes.
 */
public class JEP368TextBlockTest {

    @Test
    void test() {
        var blockHtml = """
            <html>

                <body>
                    <span>example text</span>
                </body>
            </html>""";

        String expected = "<html>\n"
                + "\n"
                + "    <body>\n"
                + "        <span>example text</span>\n"
                + "    </body>\n"
                + "</html>";
        Assertions.assertEquals(expected, blockHtml);
    }
}
