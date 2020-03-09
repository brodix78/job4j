package ru.job4j.inout;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;

public class Search {

    private HashSet<String> filesExl;

    public List<File> files(String parent, Predicate<File> search) {
        List<File> output = new ArrayList<>();
        try {
            Files.walk(Paths.get(parent)).filter(path -> search.test(path.toFile()))
                    .forEach(path -> output.add(path.toFile()));
        } catch (Exception e) {
            e.printStackTrace();
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