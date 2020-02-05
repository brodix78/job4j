package ru.job4j.list;
public class Node<T> {
    public T value;
    Node<T> next;
    Node(T value) {
        this.value = value;
    }

    boolean hasCycle(Node<T> first) {
        Node<T> turtle = first;
        Node<T> rabbit = first;
        while (rabbit.next.next != null && rabbit.next != turtle) {
            rabbit = rabbit.next.next;
            turtle = turtle.next;
        }
        return rabbit.next == turtle;
    }
}