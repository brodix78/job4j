package ru.job4j.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> toList = new ArrayList<>();
        for (int[] row : array) {
            for (int col: row) {
                toList.add(col);
            }
        }
        return toList;
    }
}
