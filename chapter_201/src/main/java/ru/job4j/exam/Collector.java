package ru.job4j.exam;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class Collector<T> {

    private final CopyOnWriteArrayList<FutureTask<T>> data = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<T> products = new CopyOnWriteArrayList<>();
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final Factory<T> factory;
    private final AtomicInteger downloaded = new AtomicInteger();
    private final AtomicInteger downloading = new AtomicInteger();
    volatile private boolean on;

    public Collector(Factory<T> factory) {
        this.factory = factory;
        this.on = true;
        executor.execute(new Storekeeper());
    }

    private class Storekeeper implements Runnable {

        @Override
        public void run() {
            while (on) {
                while (downloaded.get() == 0 && on) {
                   try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                Iterator<FutureTask<T>> iterator = data.iterator();
                while (iterator.hasNext()) {
                    FutureTask<T> one = iterator.next();
                    boolean allreadyIn;
                    if (one.isDone()) {
                        try {
                            T rsl = one.get();
                            if (rsl != null) {
                                System.out.println(downloaded.get() + "  " + rsl);
                                boolean d = products.addIfAbsent(rsl);
                            }
                            iterator.remove();
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

    public List<T> getFull() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      while (downloading.get() != 0 || downloaded.get() != 0) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return getDownloaded();
    }

    public List<T> getDownloaded() {
        List<T> list = new ArrayList<>();
        products.iterator().forEachRemaining(t -> list.add(factory.getCopy(t)));
        return list;
    }

    public void off() {
        executor.shutdownNow();
        on = false;
    }

    public static void main(String[] args) throws JsonProcessingException {
        Collector<Camera> collector = new Collector<>(new CameraFactory());
        collector.addData(new ExplorerUrl(new JsonConverter()), List.of("http://www.mocky.io/v2/5c51b9dd3400003252129fb5"));
        System.out.println(new JsonConverter().asFormat(collector.getFull()));
        System.out.println(collector.getDownloaded());
        collector.off();
    }
}
