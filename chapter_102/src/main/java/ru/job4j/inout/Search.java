package ru.job4j.inout;

import java.io.File;
import java.util.*;

public class Search {

    public List<File> files(String parent, List<String> exts) {
        File path = new File(parent);
        HashSet<String> filesExt = new HashSet<>(exts);
        LinkedList<File> directories = new LinkedList<>(List.of(path));
        List<File> output = new ArrayList<>();
        while (!directories.isEmpty()) {
            Arrays.stream(directories.pop().listFiles())
                    .forEach(file -> {
                        if (file.isDirectory()) {
                            directories.push(file);
                        } else if (filesExt.contains(file.getName()
                                .substring(file.getName().lastIndexOf(".")))) {
                            output.add(file);
                        }
                    });
        }
        return output;
    }
}