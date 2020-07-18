package ru.job4j.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class BigRouter<T> implements Callable<List<Future<T>>> {

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

    /**Scan for products generating links and creates SmallRouters which create products Factories
     *
     * @return List of Future products
     */
    @Override
    public List<Future<T>> call() {
        downloading.incrementAndGet();
        List<String> checkList = factory.links();
        List<Future<T>> rsl = new ArrayList<>();
        List<Map<String, String>> links = List.of();
        try {
            links = explorer.call();
        } catch (Exception e) {

        }
        if (links != null) {
            for (Map<String, String> map : links) {
                if (map.keySet().containsAll(checkList)) {
                    rsl.add(executor.submit(new SmallRouter<>(explorer, factory, executor,
                            map, downloaded, downloading)));
                }
            }
        }
        downloading.decrementAndGet();
        return rsl;
    }
}
