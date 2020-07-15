package ru.job4j.exam;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class SmallFactory<T extends Child> implements Callable<T> {

    private final Explorer explorer;
    private final T child;
    private final ExecutorService executor;
    private final Map<String, String> map;


    public SmallFactory(Explorer explorer, T child, ExecutorService executor, Map<String, String> map) {
        this.explorer = explorer;
        this.child = child;
        this.executor = executor;
        this.map = map;
    }

    @Override
    public T call() throws Exception {
        List<String> checkList = child.links();
        Map<String, String> fields = new TreeMap(map);
        List<Future<List<Map<String, String>>>> fromLinks = new LinkedList<>();
        for (String link : checkList) {
            Explorer exp = explorer.getInstance(map.get(link));
            Future<List<Map<String, String>>> fromLink = executor.submit(exp);
            fromLinks.add(fromLink);
        }
        for (Future<List<Map<String, String>>> fromLink : fromLinks) {
            List<Map<String, String>> parts = fromLink.get();
            if (parts.size() == 0) {
                fields.putAll(parts.get(0));
            }
        }
        return  (T) child.getInstance(map);
    }
}
