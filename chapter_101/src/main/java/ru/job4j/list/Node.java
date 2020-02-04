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
        boolean turtleGo = false;
        while (rabbit.next != null && rabbit.next != turtle) {
            rabbit = rabbit.next;
            if (turtleGo) {
                turtle = turtle.next;
            }
            turtleGo = !turtleGo;
        }
        return rabbit.next == turtle;
    }
}