package ru.job4j.generic;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> {
    private Object[] objects;
    private int index = 0;
    private int size;

    public SimpleArray(int size) {
        this.size = size;
        this.objects = new Object[size];
    }

    public void add(T element) {
        if (this.index == this.size) {
            throw new NoSuchElementException("Out of size");
        }
        objects[this.index++] = element;
    }

    public void set(int pos, T element) {
        if (pos < 0 || pos >= size) {
            this.throwOut(pos);
        }
        objects[pos] = element;
    }

    public void remove(int pos) {
        if (pos < 0 || pos >= size) {
            this.throwOut(pos);
        }
        while (++pos < size) {
            objects[pos - 1] = objects[pos];
        }
        objects[size - 1] = null;
    }

    public Object get(int pos) {
        if (pos < 0 || pos >= size) {
            this.throwOut(pos);
        }
        return (T) objects[pos];
    }

    public int size() {
        return this.size;
    }

    private void throwOut(int pos) {
        throw new NoSuchElementException(String.format("%s is out of bounds", pos));
    }
}