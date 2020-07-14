package ru.job4j.exam;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class SmallFactory<T extends Child> implements Callable<T> {

    private final Callable explorer;
    private final T child;
    private final ExecutorService executor;
    private Map<String, String> links;


    public SmallFactory(Callable explorer, T child, ExecutorService executor, Map<String, String> links) {
        this.explorer = explorer;
        this.child = child;
        this.executor = executor;
        this.links = links;
    }

    @Override
    public T call() throws Exception {
        List<String> checkList = child.links();
        for (String link : checkList) {
            Callable<> exp = new Explorer(links.get(link)) {
            };
            Future<List<Map<String, String>>> pe = executor.submit(new FutureTask<List<Map<String, String>>>(exp));
        }

        return null;
    }
}
