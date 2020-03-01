package ru.job4j.exam;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Find {
    Path root;
    String toFind;
    Predicate<String> rule;
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
                            find.root = Paths.get(args[++index]);
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
        try (Stream<Path> walk = Files.walk(root);
             BufferedWriter out = new BufferedWriter(new FileWriter(new File("./" + logTxt)))) {
            StringBuilder rsl = new StringBuilder();
            walk.filter(file -> Files.isRegularFile(file) && rule.test(file.getFileName().toString()))
                        .forEach(file -> {
                            rsl.append(file.toString());
                            rsl.append(System.lineSeparator());
                        });
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

    private class Regular implements Predicate<String> {
        @Override
        public boolean test(String file) {
            Pattern pattern = Pattern.compile(toFind);
            Matcher matcher = pattern.matcher(file);
            return matcher.find();
        }
    }

    private class Mask implements Predicate<String> {
        @Override
        public boolean test(String file) {
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + toFind);
            return matcher.matches(Paths.get(file));
        }
    }

    private class Fully implements Predicate<String> {
        @Override
        public boolean test(String file) {
            return file.equals(toFind);
        }
    }
}