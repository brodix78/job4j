package ru.job4j.exam;

import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class Collector<T> {

    private final LinkedBlockingDeque<Future<T>> data = new LinkedBlockingDeque<>();
    private final LinkedBlockingDeque<T> products = new LinkedBlockingDeque<>();
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

    /**
     * Storekeeper is checking Future in data and moving prepared objects to products
     */
    private class Storekeeper implements Runnable {

        @Override
        public void run() {
            while (on) {
                while (downloaded.get() == 0 && on) {
                   try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                Iterator<Future<T>> iterator = data.iterator();
                while (iterator.hasNext()) {
                    Future<T> one = iterator.next();
                    if (one.isDone()) {
                        try {
                            T rsl = one.get();
                            if (rsl != null) {
                                products.add(rsl);
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

    /**
     * Adding new links for scan
      * @param explorer tool for getting required data in List<Map<String, String> format
     * @param links for scan by explorer
     */
    public void addData(Explorer explorer, List<String> links) {
        for (String link : links) {
            executor.execute(() -> {
                try {
                    data.addAll(new BigRouter<>(explorer.getInstance(link), factory, executor,
                            downloaded, downloading).call());
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }

    /**
     *
     * @return List of prepared objects from products, when all downloads are done
     */
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

    /**
     *
     * @return List of objects from products, which prepared at current moment
     */
    public List<T> getDownloaded() {
        List<T> list = new ArrayList<>();
        products.iterator().forEachRemaining(t -> list.add(factory.getCopy(t)));
        return list;
    }

    /**
     * stops executor and storekeeper
     */
    public void off() {
        executor.shutdownNow();
        on = false;
    }

    public static void main(String[] args) {
        Collector<Camera> collector = new Collector<>(new CameraFactory());
        collector.addData(new ExplorerUrl(new JsonConverter()),
                List.of("http://www.mocky.io/v2/5c51b9dd3400003252129fb5"));
        System.out.println(new JsonConverter().asFormat(collector.getFull()));
        collector.off();
    }
}
