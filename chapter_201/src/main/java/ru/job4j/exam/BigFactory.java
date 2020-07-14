package ru.job4j.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

public class BigFactory<T extends Child> implements Callable<List<FutureTask<T>>> {

    private final Explorer explorer;
    private final T child;
    private final ExecutorService executor;

    public BigFactory(Explorer explorer, T t, ExecutorService executor) {
        this.explorer = explorer;
        this.child = t;
        this.executor = executor;
    }

    @Override
    public List<FutureTask<T>> call() throws Exception {
        List<String> checkList = child.links();
        List<FutureTask<T>> rsl = new ArrayList<>();
        List<Map<String, String>> downloaded = explorer.call();
        for (Map<String, String> map : downloaded) {
            if (map.keySet().containsAll(checkList)) {
                FutureTask<T> newBorn = new FutureTask<T>(new SmallFactory<T>(explorer, child, executor, map));
                rsl.add(newBorn);
                executor.submit(newBorn);
            }
        }
        return rsl;
    }
}
