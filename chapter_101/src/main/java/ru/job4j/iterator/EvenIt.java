package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt<Integer> implements Iterator {
    private int index = 0;
    private final int[] array;

    public EvenIt(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        while (this.index < array.length
                && array[this.index] % 2 != 0) {
            this.index++;
        }
        return this.index < array.length;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Array passed");
        }
        return array[this.index++];
    }
}