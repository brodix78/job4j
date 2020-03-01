package ru.job4j.inout;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Calendar;

public class Try {

    public static void main(String[] args) throws IOException {
        Path d = Paths.get("./chapter_001");
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(d)){
            for (Path path:ds) {
                System.out.println(path.getFileName());
            }
        } catch (Exception e) {
            System.out.println("ups...");
        };
    }

}
