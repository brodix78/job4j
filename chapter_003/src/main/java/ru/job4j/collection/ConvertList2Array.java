package ru.job4j.collection;

import java.util.List;

public class ConvertList2Array {
    public static int[][] toArray(List<Integer> list, int cell) {
        int group = (int) Math.ceil((double) list.size() / cell);
        int[][] toArray = new int[group][cell];
        int col = 0, row = 0;
        for (int value : list) {
            toArray[row][col] = value;
            col++;
            if (col == cell) {
                row++;
                col = 0;
            }
        }
        return toArray;
    }

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7);
        int[][] rsl = toArray(list, 3);
        for (int[] row : rsl) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
