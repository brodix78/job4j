package ru.job4j.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertList {
    public static List<Integer> toList (List<int[]> list) {
        List<Integer> toList = new ArrayList<>();
        for (int[] row : list) {
            for (int val : row) {
                toList.add(val);
            }
        }
        return toList;
    }
}