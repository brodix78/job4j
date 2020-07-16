package ru.job4j.exam;


import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class Collector {

    private final ConcurrentSkipListSet<FutureTask<Child>> data = new ConcurrentSkipListSet<>();
    private final ConcurrentSkipListSet<Child> childs = new ConcurrentSkipListSet<>();
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final Child child;

    public Collector(Child child, Converter converter) {
        this.child = child;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Iterator<FutureTask<Child>> iterator = data.iterator();
                while (iterator.hasNext()) {
                    FutureTask<Child> one = iterator.next();
                    if (one.isDone()) {
                        try {
                            Child rsl = one.get();
                            iterator.remove();
                            if (rsl != null) {
                                childs.add(rsl);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public void addData(Explorer explorer, List<String> links) {
        for (String link : links) {
            executor.execute(() -> {
                try {
                    data.addAll(new FutureTask<>(new BigFactory(explorer, child, executor)).get());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (ExecutionException e) {
                    Thread.currentThread().interrupt();;
                }
            });
        }
    }


    public String getDownloadedData(Converter converter) {

        for (FutureTask<Child> instance : data) {
            if (instance.isDone()) {
              //  instance.get();
            }
        }
        return null;
    }


}
