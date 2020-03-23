package ru.job4j.gamnit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Sum {

    long rsl = 0;

    public long sumFields(String file, String begin, String end) {
        String in = readFile(file);
        in.lines().filter(line -> line.contains(begin) && line.contains(end))
                .forEach(line -> {
                    line = line.substring(line.indexOf(begin) + begin.length(), line.lastIndexOf(end));
                    rsl = rsl + Integer.parseInt(line);
                });
        return rsl;
    }

    private String readFile(String file) {
        String input = null;
        try {
            input = new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
}
