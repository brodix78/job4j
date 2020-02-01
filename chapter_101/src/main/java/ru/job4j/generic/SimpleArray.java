package ru.job4j.generic;

import java.util.NoSuchElementException;

public class SimpleArray<T> {
    private T[] objects;
    private int index = 0;
    private int size;

    public SimpleArray(int size) {
        this.size = size;
        this.objects = (T[]) new Object[size];
    }

    public void add(T element) {
        if (this.index == this.size) {
            throw new NoSuchElementException("Out of size");
        }
        objects[this.index++] = element;
    }

    public void set(int pos, T element) {
        if (pos < 0 || pos >= size) {
            throw new NoSuchElementException(String.format("%s is out of bounds", pos));
        }
        objects[pos] = element;
    }

    public void remove(int pos) {
        if (pos < 0 || pos >= size) {
            throw new NoSuchElementException(String.format("%s is out of bounds", pos));
        }
        while (++pos < size) {
            objects[pos - 1] = objects[pos];
        }
        objects[size - 1] = null;
    }

    public T get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new NoSuchElementException(String.format("%s is out of bounds", pos));
        }
        return objects[pos];
    }

    public int size() {
        return this.size;
    }
}