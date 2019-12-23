package ru.job4j.array;

public class Defragment {
    public static String[] compress(String[] array) {
        for (int index = 0; index < array.length; index++) {
            String cell = array[index];
            if (cell == null) {
                int in = index;
                while (in < array.length - 1) {
                    array[in] = array[in + 1];
                    in++;
                }
                array[array.length - 1] = null;
            }
            System.out.print(array[index] + " ");
        }
        return array;
    }
}
