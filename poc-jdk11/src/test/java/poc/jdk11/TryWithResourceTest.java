package poc.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

// since java 9
class TryWithResourceTest {

    @Test
    void test() throws IOException {
        final InputStream input = new ByteArrayInputStream("originalString".getBytes());
        OutputStream output = new ByteArrayOutputStream(12);
        try (input;output) {
            output.write(input.readNBytes(12));
        }
    }

    @Test
    void readAllBytes() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("originalString".getBytes());

        String result = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        Assertions.assertEquals("originalString", result);
    }
}
