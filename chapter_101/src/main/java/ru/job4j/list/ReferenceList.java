package ru.job4j.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ReferenceList<E> {
    private Node<E> first;
    private Node<E> last;
    private int modCount;
    private int length;

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

    @Override
    public int hashCode() {
        return Objects.hash(modCount);
    }
}
