package ru.job4j.collection;

import java.util.HashSet;
import java.util.List;

public class FullSearch {
    public static HashSet<String> extractNumbers(List<Task> list) {
        HashSet<String> clearNumbers = new HashSet<>();
        for (Task task : list) {
            clearNumbers.add(task.getNumber());
        }
        return clearNumbers;
    }
}
