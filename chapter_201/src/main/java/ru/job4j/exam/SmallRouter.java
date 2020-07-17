package ru.job4j.exam;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class SmallRouter<T> implements Callable<T> {

    private final Explorer explorer;
    private final Factory<T> factory;
    private final ExecutorService executor;
    private final Map<String, String> map;
    private final AtomicInteger downloaded;
    private final AtomicInteger downloading;


    public SmallRouter(Explorer explorer, Factory<T> factory, ExecutorService executor, Map<String, String> map,
                       AtomicInteger downloaded, AtomicInteger downloading) {
        this.explorer = explorer;
        this.factory = factory;
        this.executor = executor;
        this.map = map;
        this.downloaded = downloaded;
        this.downloading = downloading;
    }

    @Override
    public T call() throws Exception {
        downloading.incrementAndGet();
        List<String> checkList = factory.links();
        Map<String, String> fields = new TreeMap(map);
        List<Future<List<Map<String, String>>>> fromLinks = new LinkedList<>();
        for (String link : checkList) {
            Future<List<Map<String, String>>> fromLink = executor.submit(explorer.getInstance(map.get(link)));
            fromLinks.add(fromLink);
        }
        for (Future<List<Map<String, String>>> fromLink : fromLinks) {
            List<Map<String, String>> elements = fromLink.get();
            fields.putAll(elements.get(0));
        }
        downloading.decrementAndGet();
        downloaded.incrementAndGet();
        System.out.println(factory.getInstance((fields)));
        return factory.getInstance(fields);
    }
}
