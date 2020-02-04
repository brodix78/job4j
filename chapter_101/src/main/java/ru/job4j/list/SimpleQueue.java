package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleQueue<E> {

    private SimpleStack<E> in = new SimpleStack<>();
    private SimpleStack<E> out = new SimpleStack<>();
    private int inSize;
    private int outSize;


    public boolean push(E data) {
        boolean rsl = false;
        if (data != null) {
            rsl = in.push(data);
            inSize++;
        }
        return rsl;
    }

    public E poll() {
        if (inSize > 0) {
            inSize--;
        } else if (outSize > 0) {
            do {
                out.push(in.poll());
                inSize++;
            } while (--outSize > 0);
        } else if (outSize == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        return out.poll();
    }
}