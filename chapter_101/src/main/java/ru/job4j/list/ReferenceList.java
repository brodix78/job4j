package ru.job4j.list;

import org.jetbrains.annotations.NotNull;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ReferenceList<E> implements Iterable<E> {
    private Node<E> first;
    private Node<E> last;
    private int length;
    private int modCount;

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
        if (data != null) {
            Node<E> node = new Node<>(data);
            rsl = true;
            if (length > 0) {
                this.last.next = node;
                node.previous = this.last;
            } else {
                this.first = node;
            }
            this.last = node;
            length++;
            modCount++;
        }
        return rsl;
    }

    public E get(int index) {
        Node<E> rsl;
        if (index >= length || index < 0)  {
            throw new NoSuchElementException(String.format("%s no such index in List", index));
        }
        if (index > length / 2) {
            rsl = this.last;
            for (int i = length - 1; i > index; i--) {
                rsl = rsl.previous;
            }
        } else {
            rsl = this.first;
            for (int i = 0; i < index; i++) {
                rsl = rsl.next;
            }
        }
        return rsl.data;
    }

    public int getLength() {
        return length;
    }

    private class ReferenceListIterator<E> implements Iterator<E> {
        private int expectedModCount;
        private Node<E> node = new Node<>(null);

        public ReferenceListIterator() {
            this.expectedModCount = modCount;
            this.node.next = (Node<E>) first;
        }

        @Override
        public boolean hasNext() {
            if (this.expectedModCount != modCount) {
                throw new ConcurrentModificationException("List is changed");
            }
            return node != null && node.next != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("End of List is reached");
            }
            this.node = this.node.next;
            return this.node.data;
        }
    }
    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new ReferenceListIterator<>();
    }
}
