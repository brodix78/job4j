package ru.job4j.list;

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
        E rsl = this.first.data;
        this.first = this.first.next;
        if (this.size > 0) {
            size--;
        }
        return rsl;
    }

    public E deleteByIndex(int index) {
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
        if (this.size > 0) {
            size--;
        }
        return rsl;
    }

    public E get(int index) {
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
