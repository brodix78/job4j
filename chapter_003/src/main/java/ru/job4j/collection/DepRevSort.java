package ru.job4j.collection;

import java.util.Comparator;

public class DepRevSort implements Comparator<String> {
    @Override
    public int compare(String st1, String st2) {
        return st2.compareTo(st1);
    }
}
