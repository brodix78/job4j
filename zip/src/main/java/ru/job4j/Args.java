package ru.job4j;

import java.io.File;

public class Args {
    public File directory(String dir) {
        return new File(dir);
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