package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt<Integer> implements Iterator {
    private int index = -1;
    private final int[] array;

    public EvenIt(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return nextEvenIndex() < array.length;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Array passed");
        }
        index = nextEvenIndex();
        return array[index];
    }

    private int nextEvenIndex() {
        int next = index + 1;
        while (next < array.length
                && array[next] % 2 != 0) {
            next++;
        }
        return next;
    }
}