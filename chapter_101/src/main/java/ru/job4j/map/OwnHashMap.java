package ru.job4j.map;

import org.jetbrains.annotations.NotNull;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiPredicate;

public class OwnHashMap<K, V> implements Iterable{

    private int tableSize = 16;
    private int size;
    private Cell<K, V>[] table = new Cell[16];
    private int modCount;

    private class Cell<K, V> {
        public Cell next;
        K key;
        V value;
        public Cell(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public boolean insert(K key, V value) {
        boolean rsl = false;
        int index = index(key);
        if (table[index] == null) {
            table[index] = new Cell(key, value);
            rsl = true;
        } else {
            Cell<K, V> cell = new Cell<>(null, null);
            cell.next = table[index];
            BiPredicate<K, Cell> equal;
            if (key != null) {
                equal = (k, c) -> k.equals(c.key);
            } else {
                equal = (k, c) -> c.key == null;
            }
            do {
                cell = cell.next;
                if (equal.test(key, cell)) {
                    cell.value = value;
                    rsl = true;
                    break;
                }
            } while (cell.next != null);
            if (!rsl) {
                cell.next = new Cell(key, value);
                rsl = true;
            }
        }
        return rsl;
    }

    public V get(K key) {
        Cell<V, K> cell = preCellByKey(key).next;
        return (cell == null) ? null : (V) cell.value;
    }

    public boolean delete(K key) {
        boolean rsl = false;
        Cell cell = preCellByKey(key);
        System.out.println(cell.next.value);
        cell = cell.next;
        if (cell != null) {
            cell = cell.next;
            rsl = true;
            modCount++;
        }
        return rsl;
    }

    private Cell preCellByKey(K key) {
        int index = index(key);
        Cell<K, V> cell = new Cell(null, null);
        cell.next = table[index];
        if (cell.next != null) {
            BiPredicate<K, Cell> equal;
            if (key != null) {
                equal = (k, c) -> k.equals(c.next.key);
            } else {
                equal = (k, c) -> c.next.key == null;
            }
            do {
                if (equal.test(key, cell)) {
                    break;
                }
                cell = cell.next;
            } while (cell.next != null);
        }
        return cell;
    }

    private int index(K key) {
        int h;
        return ((key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)) % tableSize;
    }

    private void changeTable(int newSize) {
        Cell<K, V>[] oldTable = this.table;
        this.table = new Cell[newSize];
        int oldSize = this.tableSize;
        this.tableSize = newSize;
        for (int i = 0; i < oldSize; i++) {
            Cell<K, V> cell = oldTable[i];
            while (cell != null) {
                insert(cell.key, cell.value);
                cell = cell.next;
            }
        }
        modCount++;
    }

    private class OwnHashMapIterator<K> implements Iterator<K> {
        private OwnHashMap.Cell cell = new Cell<K, V>(null, null);
        private OwnHashMap.Cell cellNext = new Cell<K, V>(null, null);
        private int index = -1;
        private int expModCount;

        private OwnHashMapIterator() {
            this.expModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            if (this.expModCount != modCount) {
                throw new ConcurrentModificationException("Map is changed");
            }
            if (cell.next == null) {
                while(index < tableSize - 1 && table[++index] == null) {}
                if (index != tableSize) {
                    cellNext = table[index];
                }
            } else {
                cellNext = cell.next;
            }
            return cellNext != null;
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException("End of Map is reached");
            }
            cell = cellNext;
            return (K) cell.key;
        }
    }

    @NotNull
    @Override
    public Iterator iterator() {
        return new OwnHashMapIterator();
    }
}
