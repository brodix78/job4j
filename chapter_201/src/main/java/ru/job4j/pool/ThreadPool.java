package ru.job4j.pool;

import net.jcip.annotations.ThreadSafe;
import ru.job4j.wnn.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

@ThreadSafe
public class ThreadPool {

    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(1000);

    private ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    this.tasks.poll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            }));
        }
    }

    public void run(){
        threads.forEach(Thread::start);
    }

    public void work(Runnable job) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                this.tasks.offer(job);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }
}
