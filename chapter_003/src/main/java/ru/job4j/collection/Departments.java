package ru.job4j.collection;

import java.util.*;

public class Departments {
    public static List<String> filGaps(List<String> depats) {
        List<String> rsl = new ArrayList<String>();
        Set<String> work = new HashSet<String>();
        for (String line : depats) {
            String st = "";
            for (String vol : line.split("/")) {
                st += vol;
                work.add(st);
                st += "/";
            }
        }
        rsl.addAll(work);
        return rsl;
    }

    public static void ascSort(List<String> list) {
        Collections.sort(list);
    }

    public static void descSort(List<String> list) {
        //Collections.sort(list, Collections.reverseOrder());
        Collections.sort(list, new DepRevSort());
    }
}
