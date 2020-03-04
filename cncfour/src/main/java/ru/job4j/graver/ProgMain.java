package ru.job4j.graver;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class ProgMain {
    ArrayList<String> textLines;
    Hashtable<String, Hashtable<String, String>> rules = new Hashtable<>();

    public void action() {
        readingFile("prog.txt");
        readingRules();

    }

    private void readingFile(String file) {
        textLines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(new File("./graver/prog/" + file)))) {
            String line;
            while((line = in.readLine()) != null) {
                if (!line.isEmpty() && !line.startsWith("*")) {
                    textLines.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readingRules() {
        String key = null;
        textLines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(new File("./graver/in/Glob/global.rul")))) {
            String line;
            Hashtable<String, String > rule;
            while((line = in.readLine()) != null) {
                if (!line.isEmpty() && !line.startsWith("*")) {
                    line = line.toLowerCase();
                    String[] val = line.split(" ");
                    if (!val[0].isEmpty() && val[0].startsWith("канал")) {
                        rule = new Hashtable<>();

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ProgMain pr = new ProgMain();
        pr.action();
    }
}
