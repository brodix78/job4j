package ru.job4j.pack;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private int parentLen;

    public List<File> seekBy(String root, String ext) {
        ArrayList<File> fileList = null;
        try (Stream<Path> walk = Files.walk(Paths.get(root))) {
            fileList = walk.map(Path::toFile)
                    .filter(file -> file.isFile() && (ext == null || !file.toString().endsWith(ext)))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileList;
    }

    public void pack(List<File> source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file:source) {
                zip.putNextEntry(new ZipEntry(file.getPath().substring(this.parentLen)));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int index = 0;
        int argSize = args.length;
        Args arg = new Args();
        File source = null;
        String excluding = null;
        File target = null;
        while (index < argSize) {
            switch (args[index]) {
                case ("-d"):
                    if (index < argSize - 1) {
                        source = arg.directory(args[++index]);
                    }
                    break;
                case ("-e"):
                    if (index < argSize - 1) {
                        excluding = arg.exclude(args[++index]);
                    }
                    break;
                case ("-o"):
                    if (index < argSize - 1) {
                        target = arg.output(args[++index]);
                    }
                    break;
                default: break;
            }
            index++;
        }
        if (source != null) {
            Zip zip = new Zip();
            if (target == null) {
                target = new File(source + ".zip");
            }
            zip.parentLen = source.getParent().length();
            zip.pack(zip.seekBy(source.getPath(), excluding), target);
        } else {
            System.out.println("Usage is: java -jar zip.jar -d rootDirectory"
                    + " -e *.typeOfExcludingFiles -o destinationFile");
        }
    }
}