package ru.job4j.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

public class BigFactory implements Callable<List<FutureTask<Child>>> {

    private final Explorer explorer;
    private final Child child;
    private final ExecutorService executor;

    public BigFactory(Explorer explorer, Child child, ExecutorService executor) {
        this.explorer = explorer;
        this.child = child;
        this.executor = executor;
    }

    @Override
    public List<FutureTask<Child>> call() throws Exception {
        List<String> checkList = child.links();
        List<FutureTask<Child>> rsl = new ArrayList<>();
        List<Map<String, String>> downloaded = explorer.call();
        for (Map<String, String> map : downloaded) {
            if (map.keySet().containsAll(checkList)) {
                FutureTask<Child> newBorn = new FutureTask<>(new SmallFactory(explorer, child, executor, map));
                rsl.add(newBorn);
                executor.submit(newBorn);
            }
        }
        return rsl;
    }
}
