package poc.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

class FilesTest {

    @Test
    void writeString() throws IOException {
        var path = Files.createTempFile("tmp-filetest", ".txt");
        Files.writeString(path, "hi world\r\nomar", StandardOpenOption.APPEND);
        var newPath = Files.writeString(path, "\r\nbarra", StandardOpenOption.APPEND);

        var expected = "hi world\r\nomar\r\nbarra";
        Assertions.assertEquals(expected, Files.readString(path));
        Assertions.assertSame(path, newPath);
    }

    //Reads all content from a file into a string
    @Test
    void readString() throws IOException, URISyntaxException {
        var url = getClass().getClassLoader().getResource("words.txt");
        Path path = Paths.get(url.toURI());

        var expected = "hi world 1 line\n" +
                "omar 2 line";
        Assertions.assertEquals(expected, Files.readString(path));
    }
}
