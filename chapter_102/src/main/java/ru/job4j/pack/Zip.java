package ru.job4j.pack;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public List<File> seekBy(String root, String ext) {
        LinkedList<File> directories = new LinkedList<>();
        directories.add(new File(root));
        ArrayList<File> fileList = new ArrayList<>();
        while (!directories.isEmpty()) {
            directories.addAll(Arrays.asList(Objects.requireNonNull(directories.pop().listFiles(file -> {
                if (file.isDirectory()) {
                    return true;
                } else {
                    if (ext == null || !file.getName().endsWith(ext)) {
                        fileList.add(file);
                    }
                }
                return false;
            }))));
        }
        return fileList;
    }

    public void pack(List<File> source, File target) {
        int parentLen = 0;
        if (!source.isEmpty()) {
            parentLen = source.get(0).getParent().length();
        }
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file:source) {
                zip.putNextEntry(new ZipEntry(file.getPath().substring(parentLen)));
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
                        index++;
                        source = arg.directory(args[index]);
                    }
                    break;
                case ("-e"):
                    if (index < argSize - 1) {
                        index++;
                        excluding = arg.exclude(args[index]);
                    }
                    break;
                case ("-o"):
                    if (index < argSize - 1) {
                        index++;
                        target = arg.output(args[index]);
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
            zip.pack(zip.seekBy(source.getPath(), excluding), target);
        } else {
            System.out.println("Usage is: java -jar pack.jar -d directory"
                    + " -e *.typeOfExcludingFiles -o destinationFile");
        }
    }
}