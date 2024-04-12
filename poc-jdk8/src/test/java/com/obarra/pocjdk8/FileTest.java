package com.obarra.pocjdk8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class FileTest {

    @Test
    void sequenceInputStream() throws IOException {
        InputStream first = new FileInputStream("src/test/resources/FileTest/text.txt");
        InputStream second = new FileInputStream("src/test/resources/FileTest/text2.txt");

        List<Character> characterList = new ArrayList<>();
        try (SequenceInputStream sequenceInputStream = new SequenceInputStream(first, second);
             BufferedInputStream bin = new BufferedInputStream(sequenceInputStream)) {
            int i;
            while ((i = bin.read()) != -1) {
                characterList.add((char) i);
            }
        }
        System.out.print(characterList);
    }


    @Test
    void sequenceInputStreamVector() throws IOException {
        List<String> fileNames = Arrays.asList("src/test/resources/FileTest/text.txt",
                "src/test/resources/FileTest/text2.txt",
                "src/test/resources/FileTest/text3.txt");

        Vector<InputStream> inputStreams = new Vector<>();
        for (String fileName : fileNames) {
            inputStreams.add(new FileInputStream(fileName));
        }

        List<Character> characterList = new ArrayList<>();
        try (SequenceInputStream sequenceInputStream = new SequenceInputStream(inputStreams.elements());
             BufferedInputStream bin = new BufferedInputStream(sequenceInputStream)) {
            int i;
            while ((i = bin.read()) != -1) {
                characterList.add((char) i);
            }
        }
        System.out.print(characterList);
    }


    @Test
    void sequenceInputStreamVectorsss() throws IOException {
        List<String> fileNames = Arrays.asList("src/test/resources/FileTest/text.txt",
                "src/test/resources/FileTest/text2.txt",
                "src/test/resources/FileTest/text3.txt");

        Iterator<String> iterator = fileNames.iterator();
        System.out.println(iterator.next());


        Vector<InputStream> inputStreams = new Vector<>();
        for (String fileName : fileNames) {
            inputStreams.add(new FileInputStream(fileName));
        }

        List<Character> characterList = new ArrayList<>();
        try (SequenceInputStream sequenceInputStream = new SequenceInputStream(inputStreams.elements());
             BufferedInputStream bin = new BufferedInputStream(sequenceInputStream)) {
            int i;
            while ((i = bin.read()) != -1) {
                characterList.add((char) i);
            }
        }
        System.out.print(characterList);
    }

    @Test
    void existsFile() throws IOException {
        Path path = Paths.get("does-not-exist.txt");
        Assertions.assertFalse(Files.exists(path));

        path = Paths.get("src/test/resources/FileTestNotExist");
        Assertions.assertFalse(Files.exists(path));

        path = Paths.get("src/test/resources/FileTest/audit.log");
        Assertions.assertTrue(Files.exists(path));

        path = Paths.get("src/test/resources/FileTest");
        Assertions.assertTrue(Files.exists(path));
    }

    void sortedFiles() throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get("src/test/resources/FileTest"))) {
            List<File> result = stream
                    .map(Path::toFile)
                    .sorted()
                    .collect(Collectors.toList());
            List<String> expected = Arrays.asList("audit-2024-02-07.log.gz", "audit-2024-02-08.log.gz", "audit.log", "json.json", "text.txt");
            Assertions.assertIterableEquals(
                    expected,
                    result.stream().map(File::getName).collect(Collectors.toList()));
        }
    }

    @Test
    void getParent() {
        Path path = Paths.get("./somefolder/audit.log");
        Assertions.assertEquals("./somefolder", path.getParent().toString());
    }

    @Test
    void toAbsolutePath() {
        Path path = Paths.get("./somefolder/audit.log");
        String currentDir = Paths.get("").toAbsolutePath().toString();
        Assertions.assertEquals(currentDir + "/./somefolder/audit.log", path.toAbsolutePath().toString());
    }

    @Test
    void createFolderAndFile() throws IOException {
        Files.createDirectories(Paths.get("src/test/resources/FileTest/tmp"));
        Files.write(Paths.get("src/test/resources/FileTest/tmp/log.log"), Collections.singletonList("line1"));
    }

    @Test
    void randomAccessFile() throws IOException {

        // file content is "ABCDEFGH"
        String filePath = "source.txt";

        System.out.println(new String(readCharsFromFile(filePath, 1, 5)));

        writeData(filePath, "Data", 5);
        //now file content is "ABCDEData"

        appendData(filePath, "pankaj");
        //now file content is "ABCDEDatapankaj"

    }

    private void appendData(String filePath, String data) throws IOException {
        RandomAccessFile raFile = new RandomAccessFile(filePath, "rw");
        raFile.seek(raFile.length());
        System.out.println("current pointer = " + raFile.getFilePointer());
        raFile.write(data.getBytes());
        raFile.close();

    }

    private void writeData(String filePath, String data, int seek) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        file.seek(seek);
        file.write(data.getBytes());
        file.close();
    }

    private byte[] readCharsFromFile(String filePath, int seek, int chars) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "r");
        file.seek(seek);
        byte[] bytes = new byte[chars];
        file.read(bytes);
        file.close();
        return bytes;
    }

    @Test
    void readAndWritingFile() throws IOException {
        Path path = Paths.get("/home/omarbarra/Downloads/assets-omar/omarrules.txt");
        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
            // Do the replace operation
            List<String> list = stream
                    .map(line -> line.replaceAll("test", "new"))
                    .collect(Collectors.toList());
            // Write the content back
            Files.write(path, list, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ByteArrayOutputStream can be handy in situations where you have a component that outputs its data to an OutputStream,
    // but where you need the data written as a byte array.
    @Test
    void byteArrayOutputStream() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // Writes data to the output stream
        out.write("This is a line of text".getBytes());

        String streamData = out.toString();
        System.out.println("Output stream: " + streamData);

        System.out.print("Data using toByteArray(): ");
        // Returns an array of bytes
        byte[] byteData = out.toByteArray();
        for (byte byteDatum : byteData) {
            System.out.print((char) byteDatum);
        }
        out.close();
    }

    @Test
    void byteArrayInputStream() throws IOException {
        int c;
        ByteArrayInputStream bInput = new ByteArrayInputStream("This is a line of text".getBytes());

        while ((c = bInput.read()) != -1) {
            System.out.println(Character.toUpperCase((char) c));
        }

        bInput.reset();
    }


    @Test
    void bufferedOutputStream() throws IOException {
        try (OutputStream out = Files.newOutputStream(Paths.get("/home/omarbarra/Downloads/assets-omar/omarrules.txt")); BufferedOutputStream bout = new BufferedOutputStream(out)) {
            bout.write("Omar rules tree".getBytes());
        }
    }

    @Test
    void bufferedInputStream() throws IOException {
        List<Character> characterList = new ArrayList<>();
        try (FileInputStream fin = new FileInputStream("/home/omarbarra/Downloads/assets-omar/omarrules.txt"); BufferedInputStream bin = new BufferedInputStream(fin)) {
            int i;
            while ((i = bin.read()) != -1) {
                characterList.add((char) i);
            }
        }
        System.out.print(characterList);
    }

    @Test
    void bufferedWriter() throws IOException {
        try (FileWriter writer = new FileWriter("/home/omarbarra/Downloads/assets-omar/omarrules.txt"); BufferedWriter buffer = new BufferedWriter(writer)) {
            buffer.write("Welcome to jungle");
        }
    }

    @Test
    void bufferedReader() throws IOException {
        List<Character> characterList = new ArrayList<>();
        try (FileReader fr = new FileReader("/home/omarbarra/Downloads/assets-omar/omarrules.txt"); BufferedReader br = new BufferedReader(fr)) {
            // TOOO test readline
            // br.readLine();
            int i;
            while ((i = br.read()) != -1) {
                characterList.add((char) i);
            }
        }
        System.out.print(characterList);
    }


    @Test
    void gzipOutputStreamCompressed() throws IOException {
        // file to compress
        FileInputStream fis = new FileInputStream("/home/omarbarra/Downloads/assets-omar/omarrules.txt");

        // Creating the compressed file
        FileOutputStream fos = new FileOutputStream("/home/omarbarra/Downloads/assets-omar/compress.gz");

        // Object of Fileoutstream passed
        GZIPOutputStream gzipOS = new GZIPOutputStream(fos);
        byte[] buffer = new byte[1024];
        int len;

        // Writing the data to file until -1 reached(End of file)
        while ((len = fis.read(buffer)) != -1) {
            gzipOS.write(buffer, 0, len);
        }

        // Closing the resources
        gzipOS.close();
        fos.close();
        fis.close();
    }

    @Test
    void gzipInputStreamDecompressed() throws IOException {
        try (GZIPInputStream gis = new GZIPInputStream(Files.newInputStream(Paths.get("/home/omarbarra/Downloads/assets-omar/compress.gz")));
             FileOutputStream fos = new FileOutputStream("/home/omarbarra/Downloads/assets-omar/omarrules-decompresed.txt")) {

            // copy GZIPInputStream to FileOutputStream
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        }
    }

    @Test
    void gzipInputStreamDecompressedFilesCopy() throws IOException {
        try (GZIPInputStream gis = new GZIPInputStream(Files.newInputStream(Paths.get("/home/omarbarra/Downloads/assets-omar/compress.gz")))) {
            Files.copy(gis, Paths.get("/home/omarbarra/Downloads/assets-omar/omarrules-decompresed-copy.txt"));
        }
    }

    @Test
    public void write() throws IOException {
        Path path = Paths.get("/home/omarbarra/Downloads/assets-omar/test.txt");
        Files.write(path, "Hi\n".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        Files.write(path, "world".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
    }

    @Test
    public void createTempDirectory() throws IOException {
        Path path = Files.createTempDirectory("omar-test-time.txt");
        Path tempFile = Files.createTempFile("omarfile", "dsdsd");

        System.out.println(tempFile.toAbsolutePath().toString());

        // /tmp/omar-test-time7606637596240992786: Is a directory
        Files.write(tempFile, "Hi\n".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
    }
}