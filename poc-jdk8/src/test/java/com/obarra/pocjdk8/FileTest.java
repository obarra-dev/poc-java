package com.obarra.pocjdk8;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileTest {
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