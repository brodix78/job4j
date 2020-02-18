package ru.job4j.inout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().map(e -> e.replaceAll("\\s+", ""))
                    .filter(e -> e.length() > 2)
                    .forEach(e -> mapFill(e.toCharArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mapFill(char[] line) {
        StringBuilder key = new StringBuilder();
        StringBuilder value = new StringBuilder();
        boolean first = true;
        for (char c : line) {
            if (c == '#' || c == '!') {
                break;
            }
            if (c != '=') {
                if (first) {
                    key.append(c);
                } else {
                    value.append(c);
                }
            } else {
                if (first) {
                    first = false;
                } else {
                    break;
                }
            }
        }
        if (!first && value.length() > 0) {
            values.put(key.toString(), value.toString());
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out =  new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
