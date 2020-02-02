package ru.job4j.list;
public class Node<T> {
    public T value;
    Node<T> next;
    Node(T value) {
        this.value = value;
    }
    boolean hasCycle(Node<T> first) {
        boolean rsl = false;
        Node<T> node = first;
        while (node.next != null && !rsl) {
            Node<T> label = first;
            node = node.next;
            do {
                if (label == node.next) {
                    rsl = true;
                    break;
                }
                label = label.next;
            } while (label != node);
        }
        return rsl;
    }
}