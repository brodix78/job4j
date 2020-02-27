package ru.job4j.exam;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Find {
    String root;
    String toFind;
    String option;
    String logTxt;


    public static void main(String[] args) {
        boolean allOk;
        if (args.length == 7) {
            Find find = new Find();
            allOk = true;
            int index = 0;
            int argsLen = args.length;
            while (index < argsLen && allOk) {
                if (args[index].startsWith("-")) {
                    if (args[index].equals("-m") || args[index].equals("-f") || args[index].equals("-r")) {
                        find.option = args[index].substring(1);
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
            } else {
                System.out.println("Usage is: -jar find.jar -d c:/ -n *.txt -m -o log.txt");
            }
        }
    }

    private void search() {
        try (Stream<Path> walk = Files.walk(Paths.get(root));
             BufferedWriter out = new BufferedWriter(new FileWriter(new File(root + "/" + "txt.log")))) {
            StringBuilder rsl = new StringBuilder();
            if (option.equals("r")) {
                walk.filter(file -> Files.isDirectory(file) && regular(file.getFileName().toString()))
                        .forEach(file -> rsl.append(file.toString()));
            } else if (option.equals("m")) {
                walk.filter(file -> Files.isDirectory(file) && mask(file.getFileName().toString()))
                        .forEach(file -> rsl.append(file.toString()));
            } else {
                walk.filter(file -> Files.isDirectory(file) && file.getFileName().toString().equals(toFind))
                        .forEach(file -> rsl.append(file.toString()));
                }
        out.write(rsl.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean regular(String file) {
        Pattern pattern = Pattern.compile(toFind);
        Matcher matcher = pattern.matcher(file);
        matcher.find();
        return matcher.start() == 0 && matcher.end() == file.length();
    }

    private boolean mask(String file) {
        Pattern pattern = Pattern.compile(toFind);
        Matcher matcher = pattern.matcher(file);
        matcher.find();
        return matcher.start() == 0 && matcher.end() == file.length();
    }
}