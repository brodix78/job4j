package ru.job4j.syncro;

import net.jcip.annotations.Immutable;

@Immutable
public class Node<T> {

    private final Node<T> next;
    private final T value;

    public Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public T getValue() {
        return value;
    }
}