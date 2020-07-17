package ru.job4j.exam;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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


    public SmallRouter(Explorer explorer, Factory<T> child, ExecutorService executor, Map<String, String> map,
                       AtomicInteger downloaded, AtomicInteger downloading) {
        this.explorer = explorer;
        this.factory = child;
        this.executor = executor;
        this.map = map;
        this.downloaded = downloaded;
        this.downloading = downloading;
    }

    @Override
    public T call() throws Exception {
        downloading.incrementAndGet();
        System.out.println("!");
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
            System.out.println(elements);
        }

        downloading.decrementAndGet();
        downloaded.incrementAndGet();
        downloaded.notifyAll();
        return factory.getChild(fields);
    }
}
