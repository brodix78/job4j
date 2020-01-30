package ru.job4j.list;

import java.util.NoSuchElementException;

public class ReferenceList<E> {
    private Node<E> first;
    private Node<E> last;
    private int modCount;
    private int size;

    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> previous;
        Node(E data) {
            this.data = data;
        }
    }

    public boolean add(E data) {
        boolean rsl = false;
        Node<E> node = new Node(data);
        if (data != null) {
            rsl = true;
            if (size > 0) {
                this.last.next = node;
                node.previous = this.last;
            } else {
                this.first = node;
            }
            this.last = node;
            size++;
            modCount++;
        }
        return rsl;
    }

    public E get(int index) {
        Node<E> rsl = null;
        if (index < size && index >= 0) {
            if (index > size / 2) {
                rsl = this.last;
                for (int i = size - 1; i > index; i--) {
                    rsl = rsl.previous;
                }
            } else {
                rsl = this.first;
                for (int i = 0; i < index; i++) {
                    rsl = rsl.next;
                }
            }
        } else {
            throw new NoSuchElementException(String.format("%s no such index in List", index));
        }
        return rsl.data;
    }

    public int getModCount() {
        return modCount;
    }

    public int getSize() {
        return size;
    }
}
