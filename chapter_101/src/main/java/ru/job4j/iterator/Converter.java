package ru.job4j.iterator;

import java.util.Iterator;

public class Converter<Integer> implements Iterator {

    private Iterator<Iterator<Integer>> iterators;

    private Iterator<Integer> iterator;

    public Converter(Iterator<Iterator<Integer>> iterators) {
        this.iterators = iterators;
        this.iterator = iterators.next();
    }

    @Override
    public boolean hasNext() {
        while (!this.iterator.hasNext() && this.iterators.hasNext()) {
            this.iterator = this.iterators.next();
        }
        return this.iterator.hasNext();
    }

    @Override
    public Object next() {
        if (!this.iterator.hasNext()) {
            this.iterator = this.iterators.next();
        }
        return this.iterator.next();
    }
}
