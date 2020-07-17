package ru.job4j.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class BigRouter<T> implements Callable<List<FutureTask<T>>> {

    private final Explorer explorer;
    private final Factory<T> factory;
    private final ExecutorService executor;
    private final AtomicInteger downloaded;
    private final AtomicInteger downloading;

    public BigRouter(Explorer explorer, Factory<T> factory, ExecutorService executor,
                     AtomicInteger downloaded, AtomicInteger downloading) {
        this.explorer = explorer;
        this.factory = factory;
        this.executor = executor;
        this.downloaded = downloaded;
        this.downloading = downloading;
    }

    @Override
    public List<FutureTask<T>> call() throws Exception {
        downloading.incrementAndGet();
        List<String> checkList = factory.links();
        List<FutureTask<T>> rsl = new ArrayList<>();
        List<Map<String, String>> links = explorer.call();
        for (Map<String, String> map : links) {
            if (map.keySet().containsAll(checkList)) {
                FutureTask<T> newBorn = new FutureTask<T>(new SmallRouter<T>(explorer, factory, executor,
                        map, downloaded, downloading));
                rsl.add(newBorn);
                executor.submit(newBorn);
            }
        }
        downloading.decrementAndGet();
        return rsl;
    }
}
