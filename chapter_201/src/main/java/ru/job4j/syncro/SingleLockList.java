package ru.job4j.syncro;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.jetbrains.annotations.NotNull;
import ru.job4j.list.DynamicList;

import java.util.Iterator;
import java.util.stream.Stream;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    @GuardedBy("this")
    private DynamicList<T> list = new DynamicList<>();

    public synchronized boolean add(T value) {
        return list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    private synchronized DynamicList<T> copy(DynamicList<T> original) {
        DynamicList<T> copy = new DynamicList<>();
        list.iterator().forEachRemaining(copy::add);
        return copy;
    }

    @NotNull
    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }
}
