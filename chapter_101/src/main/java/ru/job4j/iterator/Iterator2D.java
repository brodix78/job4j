package ru.job4j.iterator;

import java.util.Iterator;

public class Iterator2D<Integer> implements Iterator {
    private int f = 0;
    private int s = 0;
    private final int[][] array;

    public Iterator2D(final int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return f < array.length;
    }

    @Override
    public Object next() {
        int rsl = array[f][s];
        s++;
        if (s == array[f].length) {
            s = 0;
            f++;
        }
        return rsl;
    }
}
