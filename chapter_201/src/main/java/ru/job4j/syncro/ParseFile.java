package ru.job4j.syncro;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ParseFile {
    private volatile File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() throws IOException {
        return Files.readString(Paths.get(file.getAbsolutePath()));
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        return Files.readString(Paths.get(file.getAbsolutePath()), StandardCharsets.US_ASCII);
    }

    public synchronized void saveContent(String content) throws IOException {
        Files.writeString(Paths.get(file.getAbsolutePath()), content, StandardOpenOption.WRITE);
    }
}
