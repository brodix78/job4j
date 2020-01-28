package ru.job4j.generic;

import java.util.Iterator;

public class IteratorSimpleArray<T> implements Iterator {
    private int index = 0;
    private int size;
    private SimpleArray<T> simple;

    public IteratorSimpleArray(SimpleArray simple) {
        this.simple = simple;
    }

    @Override
    public boolean hasNext() {
        return this.index < this.simple.size()
                && this.simple.get(this.index) != null;
    }

    @Override
    public Object next() {
        return this.simple.get(index++);
    }

    @Override
    public void remove() {
        this.simple.remove(index - 1);
    }
}