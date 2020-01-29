package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter<Integer> implements Iterator {

    private Iterator<Iterator<Integer>> iterators;

    private Iterator<Integer> iterator;

    public Converter(Iterator<Iterator<Integer>> iterators) {
        this.iterators = iterators;
    }

    @Override
    public boolean hasNext() {
        try {
            while (!this.iterator.hasNext() && this.iterators.hasNext()) {
                this.iterator = this.iterators.next();
            }
        } catch (NullPointerException e) {
            this.iterator = this.iterators.next();
        }
        return this.iterator.hasNext();
    }

    @Override
    public Object next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException("Storage is over");
        }
        return this.iterator.next();
    }
}
