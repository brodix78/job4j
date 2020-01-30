package ru.job4j.iterator;

import java.util.Iterator;

public class IteratorTwoD<Integer> implements Iterator {
    private int row = 0;
    private int column = 0;
    private final int[][] array;

    public IteratorTwoD(final int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return row < array.length;
    }

    @Override
    public Object next() {
        int rsl = array[row][column];
        column++;
        if (column == array[row].length) {
            column = 0;
            row++;
        }
        return rsl;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Invalid operation for current method");
    }
}