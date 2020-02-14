package ru.job4j.exam;

import java.util.TreeSet;

public class Connections {

    public String groups(String data) {
        int index = 0;
        String row = "";
        int length = data.length();
        while (index < length && data.charAt(index) != '\n') {
            row = row + data.charAt(index++);
        }
        index++;
        String[] elements = row.split(";");
        int size = elements.length;
        String[] result =  new String[size];
        int[] counter = new int[size];
        TreeSet<String>[] buckets = new TreeSet[size];
        do {
            int gotcha = -1;
            for (int i = 0; i < size; i++) {
                if (elements[i] != null && !buckets[i].add(elements[i])) {
                    gotcha++;
                }
                if (gotcha >= 0) {
                    result[gotcha] = result[gotcha] + row + "\n";
                    counter[gotcha]++;
                }
                row = "";
                while (index < length && data.charAt(index) != '\n') {
                    row = row + data.charAt(index++);
                }
                elements = row.split(";");
            }
        } while (index < length);
        String rsl = "";
        for (int i = 0; i < size; i++) {
            rsl = rsl + "Группа " + (i + 1) + " количество элементов " + counter[i] + "\n"
                    + buckets[i] + "\n";
        }
        return rsl;
    }
}