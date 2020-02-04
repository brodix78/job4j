package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleQueue<E> {

    private SimpleStack<E> in = new SimpleStack<>();
    private SimpleStack<E> out = new SimpleStack<>();


    public boolean push(E data) {
        boolean rsl = false;
        if (data != null) {
            rsl = in.push(data);
        }
        return rsl;
    }

    public E poll() {
        E rsl;
        try {
            rsl = out.poll();
        } catch (NoSuchElementException ex) {
            boolean inEmpty = true;
            do {
                try {
                    out.push(in.poll());
                    inEmpty = false;
                } catch (NoSuchElementException e) {
                    if (inEmpty) {
                        throw new NoSuchElementException("Queue is empty");
                    } else {
                        inEmpty = true;
                    }
                }
            } while (!inEmpty);
            rsl = out.poll();
        }
        return rsl;
    }
}