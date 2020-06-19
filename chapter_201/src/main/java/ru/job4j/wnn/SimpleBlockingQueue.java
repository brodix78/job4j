package ru.job4j.wnn;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    private final Object monitor = this;
    private final int limit;

    @GuardedBy("monitor")
    private final Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    public void offer(T value) {
        synchronized (monitor) {
            while (queue.size() == limit && !Thread.currentThread().isInterrupted()) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (!Thread.currentThread().isInterrupted()) {
                queue.offer(value);
                monitor.notifyAll();
            }
        }
    }

    public T poll() {
        synchronized (monitor) {
            T val = null;
            while (queue.isEmpty() && !Thread.currentThread().isInterrupted()) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (!Thread.currentThread().isInterrupted()) {
                val = queue.poll();
                monitor.notifyAll();
            }
            return val;
        }
    }
}
