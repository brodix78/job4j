package ru.job4j.map;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

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
        int index = index(key);
        Cell cell;
        if (table[index] == null) {
            table[index] = new Cell(key, value);
        } else {
            cell = cellByKey(key, 0);
            cell.value = value;
        }
        this.size++;
        rsl = true;
        if ((double) this.size/this.tableSize > 0.75) {
            changeTable(this.tableSize * 2);
        }
        return rsl;
    }

    public V get(K key) {
        Cell cell = cellByKey(key, 1);
        return (cell == null) ? null : (V) cell.value;
    }

    public boolean delete(K key) {
        boolean rsl = false;
        Cell cell = cellByKey(key, 1);
        if (cell != null) {
            if (cell.next == null) {
                cell = null;
            } else {
                cell.value = cell.next.value;
                cell.key = cell.next.value;
                cell.next = cell.next.next;
            }
            rsl = true;
        }
        return rsl;
    }

    private Cell cellByKey(K key, int function) {
        boolean rsl;
        int index = index(key);
        Cell cell;
        int chain = 0;
        cell = table[index];
        boolean exist = false;
        while (cell.next != null) {

            if ((key != null && key.equals(cell.key))
                    || (key == null && cell.key == null)) {
                exist = true;
                break;
            }
            cell = cell.next;
        }
        if (!exist) {
            if (function == 0) {
                cell.next = new Cell(key, null);
                cell = cell.next;
            } else {
                cell = null;
            }
        }
        return cell;
    }

    private int index(K key) {
        int h;
        return ((key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)) % tableSize;
    }

    private void changeTable(int newSize) {

    }

    @NotNull
    @Override
    public Iterator iterator() {
        return null;
    }
}
