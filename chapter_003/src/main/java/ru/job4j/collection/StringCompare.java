package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String string1, String string2) {
        int l1 = string1.length();
        int l2 = string2.length();
        int min = l1 < l2 ? l1 : l2;
        int rsl = 0;
        for (int i = 0; i < min; i++) {
            rsl = Character.compare(string1.charAt(i), string2.charAt(i));
            if (rsl != 0) {
                return rsl;
            }
        }
        return l1 - l2;
    }
}
