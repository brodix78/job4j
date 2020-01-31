package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicListIterator<E> implements Iterator {
    private DynamicList<E> list;
    private int expectedModCount;
    private int index;
    private int size;

    public DynamicListIterator(DynamicList<E> list) {
        this.list = list;
        this.expectedModCount = list.hashCode();
        this.size = list.getSize();
    }

    @Override
    public boolean hasNext() {
        if (this.expectedModCount != this.list.hashCode()) {
            throw new ConcurrentModificationException("List is changed");
        }
        return this.index < this.size;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException("End of List is reached");
        }
        return this.list.get(index++);
    }
}
