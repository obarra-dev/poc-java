package poc.jdk21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

class JEP406PatternMatchingForSwitchTest {
    @Test
    void defaultEncodingIsUTF8() {
        Assertions.assertEquals("UTF-8", Charset.defaultCharset().toString());
        Assertions.assertEquals("UTF-8", System.getProperty("file.encoding"));
        Assertions.assertEquals("UTF-8", System.getProperty("file.encoding"));
    }

    @Test
    void defaultEncodingIsUTF8WandR() throws IOException {
        try (FileWriter fw = new FileWriter("happy-coding.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("ハッピーコーディング！");
        }

        String result = Files.readString(Path.of("happy-coding.txt"));

        Assertions.assertEquals("ハッピーコーディング！", result);
    }
}
