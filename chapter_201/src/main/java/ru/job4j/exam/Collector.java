package ru.job4j.exam;


import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Collector<T> {

    private final ConcurrentSkipListSet<FutureTask<T>> data = new ConcurrentSkipListSet<>();
    private final ConcurrentSkipListSet<T> products = new ConcurrentSkipListSet<>();
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final Factory<T> factory;
    private AtomicInteger downloaded = new AtomicInteger();
    private final AtomicInteger downloading = new AtomicInteger();
    private final Sorter sorter = new Sorter();

    public Collector(Factory<T> factory, Converter converter) {
        this.factory = factory;
        executor.execute(sorter);
    }

    private class Sorter implements Runnable {

        @Override
        public void run() {

            while (true) {
                System.out.println("@@");
                if (downloaded.get() == 0) {
                   try {
                        downloaded.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                Iterator<FutureTask<T>> iterator = data.iterator();
                while (iterator.hasNext()) {
                    FutureTask<T> one = iterator.next();
                    if (one.isDone()) {
                        try {
                            T rsl = one.get();
                            iterator.remove();
                            if (rsl != null) {
                                products.add(rsl);
                                this.notifyAll();
                            }
                        } catch (Exception e) {
                            Thread.currentThread().interrupt();
                        }
                        if (downloaded.decrementAndGet() == 0) {
                            break;
                        }
                    }
                }
            }
        }
    }

    public void addData(Explorer explorer, List<String> links) {
        for (String link : links) {
            executor.execute(() -> {
                try {
                    data.addAll(new BigRouter<T>(explorer.getInstance(link), factory, executor,
                            downloaded, downloading).call());
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }


    public String getDownloadedData(Converter<T> converter) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      /*  while (downloading.get() != 0 || downloaded.get() != 0) {
            try {
                sorter.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        return null;
    }

    public void stop() {
        executor.shutdownNow();
    }

    private static void main(String[] args) {
        Collector<Camera> collector = new Collector<>(new CameraFactory(), new JsonConverter());
        collector.addData(new ExplorerUrl(new JsonConverter()), List.of("http://www.mocky.io/v2/5c51b9dd3400003252129fb5"));
        System.out.println(collector.getDownloadedData(new JsonConverter()));
    }
}
