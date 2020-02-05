package ru.job4j.map;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.function.BiPredicate;

public class OwnHashMap<K, V> implements Iterable{

    private int tableSize = 16;
    private int size;
    private Cell[] table = new Cell[16];

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
        boolean rsl;
        Cell cell = preCellByKey(key);
        if (cell.next == null) {
            cell.next = new Cell(key, value);
        } else {
            cell.next.value = value;
        }
        this.size++;
        rsl = true;
        if ((double) this.size/this.tableSize > 0.75) {
            changeTable(this.tableSize * 2);
        }
        return rsl;
    }

    public V get(K key) {
        Cell cell = preCellByKey(key).next;
        return (cell == null) ? null : (V) cell.value;
    }

    public boolean delete(K key) {
        boolean rsl = false;
        Cell cell = preCellByKey(key);
        if (cell.next != null) {
            cell.next = cell.next.next;
            rsl = true;
        }
        return rsl;
    }

    private Cell preCellByKey(K key) {
        int index = index(key);
        Cell cell = new Cell(null, null);
        cell.next = table[index];
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
        return cell;
    }

    private int index(K key) {
        int h;
        return ((key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)) % tableSize;
    }

    private void changeTable(int newSize) {
        Cell[] newTable = new Cell[newSize];

    }

    @NotNull
    @Override
    public Iterator iterator() {
        return null;
    }
}
