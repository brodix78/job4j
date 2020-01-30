package ru.job4j.list;

import java.util.NoSuchElementException;

public class DynamicList<E> {
    E[] container;
    private int modCount = 0;
    private int capacity = 100;
    private int size;

    public DynamicList() {
        this.container = (E[]) new Object[this.capacity];
    }

    public int getModCount() {
        return this.modCount;
    }

    public int getSize() {
        return this.size;
    }

    public boolean add(E value) {
        boolean rsl = false;
        if (value != null) {
            this.modCount++;
            if (++this.size == this.capacity) {
                changeSize(this.capacity * 2);
            }
            this.container[this.size - 1] = value;
            rsl = true;
        }
        return rsl;
    }

    public E get(int index) {
        E rsl;
        if (index < this.size && index >= 0) {
            rsl = this.container[index];
        } else {
            throw new NoSuchElementException("No such index in List");
        }
        return rsl;
    }

    private void changeSize(int newSize) {
        E[] newContainer = (E[]) new Object[newSize];
        System.arraycopy(this.container, 0, newContainer, 0, size);
        this.container = newContainer;
    }
}