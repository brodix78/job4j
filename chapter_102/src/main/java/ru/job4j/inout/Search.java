package ru.job4j.inout;

import java.io.File;
import java.io.FileFilter;
import java.util.*;

public class Search {

    public List<File> files(String parent, List<String> exts) {
        File path = new File(parent);
        HashSet<String> filesExt = new HashSet<>(exts);
        LinkedList<File> files = new LinkedList<>(List.of(path));
        List<File> output = new ArrayList<>();
        do {
            File file = files.pop();
            if (file.isFile()) {
                output.add(file);
            } else {
                files.addAll(Arrays.asList(Objects.requireNonNull(file.listFiles(pathname -> {
                    if (pathname.isDirectory()) {
                        return true;
                    } else return filesExt.contains(pathname.getName()
                            .substring(1 + pathname.getName().lastIndexOf(".")));
                }))));
            }
        } while (!files.isEmpty());
        return output;
    }
}