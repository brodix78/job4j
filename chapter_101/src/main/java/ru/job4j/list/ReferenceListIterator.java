package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ReferenceListIterator<E> implements Iterator<E> {
    private ReferenceList<E> list;
    private int index;
    private int size;

    public ReferenceListIterator(ReferenceList<E> list) {
        this.list = list;
        this.size = list.getLength();
    }

    @Override
    public boolean hasNext() {
        if (this.size != this.list.getLength()) {
            throw new ConcurrentModificationException("List is changed");
        }
        return this.index < this.size;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException("End of List is reached");
        }
        return this.list.get(this.index++);
    }
}
