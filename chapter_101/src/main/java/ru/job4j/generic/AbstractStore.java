package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractStore<E> implements Store {
    private SimpleArray<E> abStore;

    public AbstractStore(int size) {
        this.abStore = new SimpleArray<E>(size);
    }

    @Override
    public void add(Base model) {
        try {
            abStore.add((E) model);
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Storage is full");
        }
    }

    @Override
    public boolean replace(String id, Base model) {
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

    @Override
    public boolean delete(String id) {
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

    @Override
    public Base findById(String id) {
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
