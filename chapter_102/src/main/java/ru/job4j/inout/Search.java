package ru.job4j.inout;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;

public class Search {

    private HashSet<String> filesExl;

    public List<File> files(String parent, Predicate<File> search) {
        File path = new File(parent);
        List<File> output = new ArrayList<>();
        for (File file : Objects.requireNonNull(path.listFiles())) {
            if (file.isDirectory()) {
                output.addAll(files(file.toString(), search));
            } else if (search.test(file)) {
                output.add(file);
            }
        }
        return output;
    }

    private Predicate<File> searchByTypeExclude = new Predicate<>() {
        @Override
        public boolean test(File file) {
            return filesExl.contains(file.getName()
                    .substring(file.getName().lastIndexOf(".") + 1));
        }
    };

    public List<File> filesByTypeExclude(String parent, List<String> excl) {
        this.filesExl = new HashSet<>(excl);
        return files(parent, searchByTypeExclude);
    }
}