package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleStack<E> {
    private Node<E> last;

    private static class Node<E> {
        E data;
        Node<E> previous;
        Node(E data) {
            this.data = data;
        }
    }

    public boolean push(E data) {
        boolean rsl = false;
        if (data != null) {
            Node<E> node = new Node<>(data);
            rsl = true;
            node.previous = this.last;
            this.last = node;
            }
        return rsl;
    }

    public E poll() {
        Node<E> rsl;
        if (this.last == null)  {
            throw new NoSuchElementException("Stack is empty");
        }
        rsl = this.last;
        this.last = this.last.previous;
        return rsl.data;
    }
}
