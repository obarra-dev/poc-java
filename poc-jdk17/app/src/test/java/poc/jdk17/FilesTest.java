package poc.jdk17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class FilesTest {
    @Test
    void mismatch() throws IOException {
        Path filePath1 = Files.createTempFile("file1", ".txt");
        Path filePath2 = Files.createTempFile("file2", ".txt");
        Files.writeString(filePath1, "Java 12 Article");
        Files.writeString(filePath2, "Java 12 Article");

        long mismatch = Files.mismatch(filePath1, filePath2);
        Assertions.assertEquals(-1, mismatch);

        Path filePath4 = Files.createTempFile("file4", ".txt");
        Files.writeString(filePath4, "Java 12 Tutorial");

        mismatch = Files.mismatch(filePath1, filePath4);
        // return 8L as it’s the first different byte
        Assertions.assertEquals(8, mismatch);
    }
}
