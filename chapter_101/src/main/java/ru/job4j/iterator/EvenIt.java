package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt<Integer> implements Iterator {
    private int index = -1;
    private final int[] array;
    private int nextIndex;

    public EvenIt(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        nextEvenIndex();
        return nextIndex < array.length;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Array passed");
        }
        this.index = this.nextIndex;
        return array[this.index];
    }

    private void nextEvenIndex() {
        this.nextIndex = this.index + 1;
        while (this.nextIndex < array.length
                && array[this.nextIndex] % 2 != 0) {
            this.nextIndex++;
        }
    }
}