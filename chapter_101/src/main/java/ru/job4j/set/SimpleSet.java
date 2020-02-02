package ru.job4j.set;

import org.jetbrains.annotations.NotNull;
import ru.job4j.list.DynamicList;
import ru.job4j.list.DynamicListIterator;
import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    private DynamicList<E> array = new DynamicList<>();

    public boolean add(E value) {
        boolean rsl = false;
        if (value != null) {
            rsl = true;
            for (int i = 0; i < array.getSize(); i++) {
                if (value.equals(array.get(i))) {
                    rsl = false;
                    break;
                }
            }
        }
        if (rsl) {
            rsl = array.add(value);
        }
        return rsl;
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new DynamicListIterator<>(array);
    }
}