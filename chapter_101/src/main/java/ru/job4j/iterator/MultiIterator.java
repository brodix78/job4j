package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MultiIterator<Integer> implements Iterator {
    private Iterator<Integer> out;
    private Iterator<Iterator<Integer>> in;

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        this.in = it;
        nextOut();
        return this.out;
    }

    private void nextOut() {
        if (this.in.hasNext()) {
            this.out = this.in.next();
        } else {
            throw new NoSuchElementException("Iterators over");
        }
    }

    @Override
    public boolean hasNext() {
        boolean rsl = false;
        if (this.out.hasNext()) {
            rsl = true;
        } else {
            try {
                do {
                    this.nextOut();
                } while (!this.out.hasNext());
                rsl = true;
            } catch (NoSuchElementException ex) {
                rsl = false;
            }
        }
        return rsl;
    }

    @Override
    public Object next() {
        int rsl;
        if (this.out.hasNext()) {
            rsl = (int) this.out.next();
        } else {
            nextOut();
            rsl = (int) this.out.next();
        }
        return rsl;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Invalid operation for current method");
    }
}
