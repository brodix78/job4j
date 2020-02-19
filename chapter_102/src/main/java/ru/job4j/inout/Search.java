package ru.job4j.inout;

import java.io.File;
import java.util.*;

public class Search {

    public List<File> files(String parent, List<String> exts) {
        File path = new File(parent);
        HashSet<String> filesExt = new HashSet<>(exts);
        LinkedList<File> directories = new LinkedList<>(List.of(path));
        List<File> output = new ArrayList<>();
        do {
            File directory = directories.pop();
            directories.addAll(Arrays.asList(directory.listFiles(pathname -> {
                if (pathname.isDirectory()) {
                    return true;
                } else {
                    if (filesExt.contains(pathname.getName()
                            .substring(1 + pathname.getName().lastIndexOf(".")))) {
                            output.add(pathname);
                    }
                }
                return false;
            })));
        } while (!directories.isEmpty());
        return output;
    }
}