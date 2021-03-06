package ru.job4j.tree;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class OwnTree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;
    private int modCount;
    private int size;

    public OwnTree(E rootValue) {
        this.root = new Node<>(rootValue);
        size = 1;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        if (parent != null && child != null) {
            Optional<Node<E>> branch = findBy(parent);
            if (branch.isPresent()) {
                if (!findBy(child).isPresent()) {
                    branch.get().add(new Node<>(child));
                    rsl = true;
                    modCount++;
                    size++;
                }
            }
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    public boolean isBinary() {
        boolean rsl = true;
        Queue<Node<E>> tree = new LinkedList<>();
        tree.offer(this.root);
        while (!tree.isEmpty()) {
            Node<E> leaf = tree.poll();
            if (leaf.leaves().size() > 2) {
                rsl = false;
                break;
            }
            for (Node<E> kid : leaf.leaves()) {
                tree.offer(kid);
            }
        }
        return rsl;
    }

    private class OwnTreeIterator implements Iterator<E> {
        private int expectedMdCount;
        private int counter;
        private Queue<Node<E>> tree = new LinkedList<>();

        private OwnTreeIterator() {
            this.expectedMdCount = modCount;
            tree.offer(root);
        }

        @Override
        public boolean hasNext() {
            if (this.expectedMdCount != modCount) {
                throw new ConcurrentModificationException("Map is changed");
            }
            return this.counter < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Map is empty");
            }
            Node<E> leaf = this.tree.poll();
            counter++;
            for (Node<E> kid : leaf.leaves()) {
                this.tree.offer(kid);
            }
            return leaf.getValue();
        }
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new OwnTreeIterator();
    }
}
