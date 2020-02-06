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
        System.out.println(index);
        if (table[index] == null) {
            table[index] = new Cell(key, value);
            rsl = true;
            size++;
            modCount++;
        } else {
            if ((key != null && key.equals(table[index].key))
                    || (key == null && table[index].key == null)) {
                table[index].value = value;
                rsl = true;
                modCount++;
            } else {
                Cell<K, V> cell = preCellByKey(key, table[index]);
                if (cell.next == null) {
                    cell.next = new Cell(key, value);
                    rsl = true;
                    modCount++;
                } else {
                    cell.next.value = value;
                    rsl = true;
                    modCount++;
                }
            }
        }
        if (size > 0.75 * tableSize) {
            changeTable(tableSize * 2);
        }
        return rsl;
    }

    public V get(K key) {
        V val = null;
        int index = index(key);
        if (table[index] != null) {
            if ((key != null && key.equals(table[index].key))
                    || (key == null && table[index].key == null)) {
                val = table[index].value;
            } else {
                Cell cell = preCellByKey(key, table[index]);
                if (cell.next != null) {
                    val = (V) cell.next.value;
                }
            }
        }
        return val;
    }

    public boolean delete(K key) {
        int index = index(key);
        boolean rsl = false;
        if (table[index] != null) {
            if ((key != null && key.equals(table[index].key))
                    || (key == null && table[index].key == null)) {
                table[index] = table[index].next;
                rsl = true;
                modCount++;
            } else {
                Cell cell = preCellByKey(key, table[index]);
                cell.next = cell.next.next;
                rsl = true;
                modCount++;
            }
        }
        return rsl;
    }

    private Cell preCellByKey(K key, Cell startCell) {
        Cell<K, V> cell = new Cell(null, null);
        cell.next = startCell;
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
        private OwnHashMap.Cell cellNext = null;
        private int index = 0;
        private int expModCount;

        private OwnHashMapIterator() {
            this.expModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            if (this.expModCount != modCount) {
                throw new ConcurrentModificationException("Map is changed");
            }
            if (cell.next == null || cellNext == null) {
                while(index < tableSize && table[index] == null) {
                    index++;
                }
                if (index < tableSize) {
                    System.out.println("index" + index);
                    cellNext = table[index++];
                    System.out.println("key" + cellNext.key);
                }
            }
            return cellNext != null || cell.next != null;
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException("End of Map is reached");
            }
            cell = (cell.next != null) ? cell.next : cellNext;
            cellNext = null;
            return (K) cell.key;
        }
    }

    @NotNull
    @Override
    public Iterator iterator() {
        return new OwnHashMapIterator();
    }
}
