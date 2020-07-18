package ru.job4j.exam;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
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

    /**
     * Scans links with explorer and supply received data as a initialization Map for Factory
     * @return One instance of factory product
     */
    @Override
    public T call() {
        downloading.incrementAndGet();
        List<String> checkList = factory.links();
        Map<String, String> fields = new TreeMap<>(map);
        List<Future<List<Map<String, String>>>> fromLinks = new LinkedList<>();
        for (String link : checkList) {
            Future<List<Map<String, String>>> fromLink = executor.submit(explorer.getInstance(map.get(link)));
            fromLinks.add(fromLink);
        }
        for (Future<List<Map<String, String>>> fromLink : fromLinks) {
            List<Map<String, String>> elements;
            try {
                elements = fromLink.get();
            } catch (Exception e) {
                fields = null;
                break;
            }
            if (elements != null && elements.size() > 0) {
                fields.putAll(elements.get(0));
            }
        }
        downloaded.incrementAndGet();
        downloading.decrementAndGet();
        return fields != null ? factory.getInstance(fields) : null;
    }
}
