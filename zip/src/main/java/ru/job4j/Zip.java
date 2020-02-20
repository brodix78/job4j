package ru.job4j;

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
            fileList.addAll(Arrays.asList(Objects.requireNonNull(directories.pop().listFiles(file -> {
                if (!file.isDirectory()) {
                    return true;
                } else {
                    if (!file.getName().endsWith(ext)) {
                        fileList.add(file);
                    }
                }
                return false;
            }))));
        }
        return fileList;
    }

    public void pack(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry((source.getPath())));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
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
                        source = arg.directory(args[index]);
                        index++;
                    }
                    break;
                case ("-e"):
                    if (index < argSize - 1) {
                        excluding = arg.exclude(args[index]);
                        index++;
                    }
                    break;
                case ("-o"):
                    if (index < argSize - 1) {
                        target = arg.output(args[index]);
                        index++;
                    }
                    break;
                default: break;
            }
            index++;
        }
        if (source != null) {
            Zip zip = new Zip();
            if (target == null) {
                target = new File(source.getAbsolutePath() + ".zip");
            }

            for (File file : zip.seekBy(source.getAbsolutePath(), excluding)) {
                zip.pack(file, target);
            }
        } else {
            System.out.println("Usage is: java -jar pack.jar -d directory"
                    + " -e *.typeOfExcludingFiles -o destinationFile");
        }
    }
}
