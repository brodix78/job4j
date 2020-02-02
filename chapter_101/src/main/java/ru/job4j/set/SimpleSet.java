package ru.job4j.set;

import org.jetbrains.annotations.NotNull;
import ru.job4j.list.DynamicList;
import ru.job4j.list.DynamicListIterator;

import java.util.Iterator;
import java.util.function.Consumer;

public class SimpleSet<E> implements Iterable<E> {
    private DynamicList<E> array = new DynamicList<>();

    public boolean add(E value) {
        boolean exist = false;
        if (value != null) {
            for (int i = 0; i < array.getSize(); i++) {
                if (value.equals(array.get(i))) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                array.add(value);
            }
        }
        return !exist;
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new DynamicListIterator<>(array);
    }
}
