package ru.job4j.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class DynamicList<E> {
    E[] container;
    private int capacity = 100;
    private int size;

    public DynamicList() {
        this.container = (E[]) new Object[this.capacity];
    }

    public int getSize() {
        return this.size;
    }

    public boolean add(E value) {
        boolean rsl = false;
        if (value != null) {
            if (++this.size == this.capacity) {
                E[] newContainer = (E[]) new Object[this.capacity * 2];
                System.arraycopy(this.container, 0, newContainer, 0, size);
                this.container = newContainer;
            }
            this.container[this.size - 1] = value;
            rsl = true;
        }
        return rsl;
    }

    public E get(int index) {
        E rsl;
        if (index >= this.size || index < 0) {
            throw new NoSuchElementException("No such index in List");
        }
        rsl = this.container[index];
        return rsl;
    }
}