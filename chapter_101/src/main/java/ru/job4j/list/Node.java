package ru.job4j.list;

public class Node<T> {
    public T value;
    Node<T> next;
    Node(T value) {
        this.value = value;
    }

    boolean hasCycle(Node<T> first) {
        class Label {
            Node<T> data;
            Label nextLabel;
            Label(Node<T> reference) {
                this.data = reference;
            }
        }
        boolean rsl = false;
        Label firstLabel = new Label(first.next);
        Node<T> node = first;
        while (node.next != null && !rsl) {
            node = node.next;
            Label label = firstLabel;
            Label lastLabel = new Label(node);
            do {
                if (label.data == node.next) {
                    rsl = true;
                    break;
                }
                if (label.nextLabel != null) {
                    label = label.nextLabel;
                }
            } while (label.nextLabel != null);
            label.nextLabel = lastLabel;
        }
        return rsl;
    }
}
