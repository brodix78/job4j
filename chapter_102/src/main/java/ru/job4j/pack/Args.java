package ru.job4j.pack;

import java.io.File;

public class Args {
    public File directory(String dir) {
        File rsl = new File(dir);
        if (!rsl.isDirectory()) {
            rsl = null;
        }
        return rsl;
    }

    public String exclude(String exc) {
        if (exc.startsWith("*.") && exc.length() > 2) {
            exc = exc.substring(1);
        } else {
            exc = null;
        }
        return exc;
    }

    public File output(String out) {
        return new File(out);
    }
}