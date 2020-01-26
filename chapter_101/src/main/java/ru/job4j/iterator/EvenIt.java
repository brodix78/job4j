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
        index = nextEvenIndex();
        if (index == array.length) {
            throw new NoSuchElementException("Array passed");
        }
        return array[index];
    }

    private int nextEvenIndex() {
        int next = index + 1;
        while (next < array.length
                && ((float) array[next]) % 2 != 0) {
            next++;
        }
        return next;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Invalid operation for current method");
    }
}