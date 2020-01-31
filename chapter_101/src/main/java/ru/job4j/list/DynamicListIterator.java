package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicListIterator<E> implements Iterator<E> {
    private DynamicList<E> list;
    private int index;
    private int size;

    public DynamicListIterator(DynamicList<E> list) {
        this.list = list;
        this.size = list.getSize();
    }

    @Override
    public boolean hasNext() {
        if (this.size != this.list.getSize()) {
            throw new ConcurrentModificationException("List is changed");
        }
        return this.index < this.size;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException("End of List is reached");
        }
        return this.list.get(index++);
    }
}
