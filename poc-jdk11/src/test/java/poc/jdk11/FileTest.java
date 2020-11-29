package poc.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class FileTest {

    @Test
    void writeString() throws IOException {
        var path = Files.createTempFile("tmp-filetest", ".txt");
        var newPath = Files.writeString(path, "hi world\n omar");

        Assertions.assertSame(path, newPath);
    }

    //Reads all content from a file into a string
    @Test
    void readString() throws IOException, URISyntaxException {
        var url = getClass().getClassLoader().getResource("words.txt");
        Path path = Paths.get(url.toURI());

        var expected = "hi world 1 line\r\nomar 2 line";
        Assertions.assertEquals(expected, Files.readString(path));
    }
}
