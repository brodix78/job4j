package ru.job4j.list;

import org.jetbrains.annotations.NotNull;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicList<E> implements Iterable<E> {
    E[] container;
    private int capacity = 100;
    private int size;
    private int modCount;

    public DynamicList() {
        this.container = (E[]) new Object[this.capacity];
    }

    public int getSize() {
        return this.size;
    }

    public boolean add(E value) {
        boolean rsl;
        if (++this.size == this.capacity) {
                changeSize(this.capacity * 2);
            }
        this.container[this.size - 1] = value;
        this.modCount++;
        rsl = true;
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

    private void changeSize(int newSize) {
        E[] newContainer = (E[]) new Object[newSize];
        System.arraycopy(this.container, 0, newContainer, 0, size);
        this.container = newContainer;
    }

    private class DynamicListIterator<E> implements Iterator<E> {
        private int index;
        private int expectedModCount;

        private DynamicListIterator() {
            this.expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            if (this.expectedModCount != modCount) {
                throw new ConcurrentModificationException("List is changed");
            }
            return this.index < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("End of List is reached");
            }
            return (E) get(index++);
        }
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new DynamicListIterator<>();
    }
}