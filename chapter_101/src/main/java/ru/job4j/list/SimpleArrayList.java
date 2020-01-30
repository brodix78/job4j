package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleArrayList<E> {
    private int size;
    private Node<E> first;

    private static class Node<E> {
        E data;
        Node<E> next;
        Node(E data) {
            this.data = data;
        }
    }

    public void add(E data) {
        Node newLink = new Node(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    public E deleteFirst() {
        if (this.size == 0) {
            throw new NullPointerException("No elements in List");
        }
        E rsl = this.first.data;
        this.first = this.first.next;
        size--;
        return rsl;
    }

    public E deleteByIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new NoSuchElementException("Out of List's bounds");
        }
        Node<E> node = this.first;
        E rsl;
        if (index == 0) {
            rsl = deleteFirst();
        } else {
            for (int i = 0; i < index - 1; i++) {
                node = node.next;
            }
            rsl = node.next.data;
            node.next = node.next.next;
        }
        size--;
        return rsl;
    }

    public E get(int index) {
        if (index < 0 || index >= this.size) {
            throw new NoSuchElementException("Out of List's bounds");
        }
        Node<E> rsl = this.first;
        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl.data;
    }

    public int getSize() {
        return this.size;
    }
}
