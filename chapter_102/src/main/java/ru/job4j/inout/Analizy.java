package ru.job4j.inout;

import java.io.*;

public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            String line;
            StringBuilder timeSection = new StringBuilder();
            Boolean haveAccess = true;
            while ((line = reader.readLine()) != null) {
                if (line.charAt(0) > '3') {
                    if (haveAccess) {
                        haveAccess = false;
                        timeSection.append(line.substring(4));
                        timeSection.append(";");
                    }
                } else {
                    if (!haveAccess) {
                        haveAccess = true;
                        timeSection.append(line.substring(4));
                        timeSection.append(System.lineSeparator());
                        writer.write(timeSection.toString());
                        timeSection.setLength(0);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}