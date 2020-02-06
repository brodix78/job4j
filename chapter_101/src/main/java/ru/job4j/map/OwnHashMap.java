package ru.job4j.map;
import org.jetbrains.annotations.NotNull;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiPredicate;

public class OwnHashMap<K, V> implements Iterable<K> {

    private int tableSize = 16;
    private int size;
    private Cell<K, V>[] table = new Cell[tableSize];
    private int modCount;

    private class Cell<K, V> {
        public Cell<K, V> next;
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
        if (table[index] == null) {
            table[index] = new Cell<>(key, value);
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
                    cell.next = new Cell<>(key, value);
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
                Cell<K, V> cell = preCellByKey(key, table[index]);
                if (cell.next != null) {
                    val = cell.next.value;
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
                Cell<K, V> cell = preCellByKey(key, table[index]);
                cell.next = cell.next.next;
                rsl = true;
                modCount++;
            }
        }
        return rsl;
    }

    private Cell<K, V> preCellByKey(K key, Cell<K, V> startCell) {
        Cell<K, V> cell = new Cell<>(null, null);
        cell.next = startCell;
        BiPredicate<K, Cell<K, V>> equal;
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
        if (key == null) {
            h = 0;
        } else {
            h = key.hashCode();
            h = (h ^ (h >>> 16)) % tableSize;
        }
        return h;
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

    private class OwnHashMapIterator implements Iterator<K> {

        private Cell<K, V> cell = null;
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
            if (cell == null) {
                do {
                    index++;
                } while (index < tableSize && table[index] == null);
                if (index < tableSize) {
                    cell = table[index];
                }
            }
            return cell != null;
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException("End of Map is reached");
            }
            K key = cell.key;
            cell = cell.next;
            return key;
        }
    }

    @NotNull
    @Override
    public Iterator<K> iterator() {
        return new OwnHashMapIterator();
    }
}