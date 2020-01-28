package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractStore<E> {

    private SimpleArray<E> abStore;

    protected void newStore(int size) {
        this.abStore = new SimpleArray<E>(size);
    }

    protected void add(Base model) {
        try {
            abStore.add((E) model);
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Storage is full");
        }
    }

    protected boolean replace(String id, Base model) {
        boolean rsl = false;
        Iterator<Base> it = new IteratorSimpleArray(this.abStore);
        int index = 0;
        while (it.hasNext() || !rsl) {
            if (it.next().getId().equals(id)) {
                rsl = true;
                this.abStore.set(index, (E) model);
            }
            index++;
        }
        return rsl;
    }

    protected boolean delete(String id) {
        boolean rsl = false;
        Iterator<Base> it = new IteratorSimpleArray(this.abStore);
        int index = 0;
        while (it.hasNext() || !rsl) {
            if (it.next().getId().equals(id)) {
                rsl = true;
                this.abStore.remove(index);
            }
            index++;
        }
        return rsl;
    }

    protected Base findById(String id) {
        Base rsl = null;
        Iterator<Base> it = new IteratorSimpleArray(this.abStore);
        int index = 0;
        while (it.hasNext() && rsl == null) {
            if (it.next().getId().equals(id)) {
                rsl = (Base) this.abStore.get(index);
            }
            index++;
        }
        return rsl;
    }
}
