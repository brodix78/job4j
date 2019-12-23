package ru.job4j.array;

public class Turn {
    public int[] back(int[] array) {
        int cycleLong = array.length / 2;
        for (int i = 0; i < cycleLong; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return array;
    }
}