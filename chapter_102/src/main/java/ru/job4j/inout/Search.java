package ru.job4j.inout;

import java.io.File;
import java.util.*;

public class Search {

    public List<File> files(String parent, List<String> exts) {
        File path = new File(parent);
        HashSet<String> filesExt = new HashSet<>(exts);
        List<File> output = new ArrayList<>();
        for (File file : path.listFiles()) {
            if (file.isDirectory()) {
                output.addAll(files(file.toString(), exts));
            } else if (filesExt.contains(file.getName()
                    .substring(file.getName().lastIndexOf(".") + 1))) {
                output.add(file);
            }
        }
        return output;
    }
}