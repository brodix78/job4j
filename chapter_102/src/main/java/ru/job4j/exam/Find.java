package ru.job4j.exam;

import ru.job4j.inout.Search;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Find {
    String root;
    String toFind;
    Predicate<File> rule;
    String logTxt;

    public static void main(String[] args) {
        boolean allOk = false;
        if (args.length == 7) {
            Find find = new Find();
            allOk = true;
            int index = 0;
            int argsLen = args.length;
            while (index < argsLen && allOk) {
                if (args[index].startsWith("-")) {
                    if (args[index].equals("-m") || args[index].equals("-f") || args[index].equals("-r")) {
                        find.setRule(args[index]);
                    } else if (index + 1 < argsLen) {
                        if (args[index].equals("-d")) {
                            find.root = args[++index];
                        } else if (args[index].equals("-n")) {
                            find.toFind = args[++index];
                        } else if (args[index].equals("-o")) {
                            find.logTxt = args[++index];
                        } else {
                            allOk = false;
                        }
                    } else {
                        allOk = false;
                    }
                }
                index++;
            }
            if (allOk) {
                find.search();
            }
        }
        if (!allOk) {
            System.out.println("Usage is: -jar find.jar -d c:/ -n *.txt -m -o log.txt");
        }
    }

    private void search() {
        Search search =  new Search();
        StringBuilder rsl = new StringBuilder();
        search.files(root, rule).stream().forEach(file -> rsl.append(String.format("%s%n", file)));
        try (BufferedWriter out = new BufferedWriter(new FileWriter(new File("./" + logTxt)))) {
        out.write(rsl.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setRule(String option) {
        if ("-r".equals(option)) {
            rule = new Regular();
        } else if ("-m".equals(option)) {
            rule = new Mask();
        } else {
            rule = new Fully();
        }
    }

    private class Regular implements Predicate<File> {
        @Override
        public boolean test(File file) {
            Pattern pattern = Pattern.compile(toFind);
            Matcher matcher = pattern.matcher(file.getName());
            return matcher.find();
        }
    }

    private class Mask implements Predicate<File> {
        @Override
        public boolean test(File file) {
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + toFind);
            return matcher.matches(Paths.get(file.getName()));
        }
    }

    private class Fully implements Predicate<File> {
        @Override
        public boolean test(File file) {
            return file.getName().equals(toFind);
        }
    }
}