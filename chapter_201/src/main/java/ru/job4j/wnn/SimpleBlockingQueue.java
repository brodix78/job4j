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

    public void offer(T value) throws InterruptedException{
        synchronized (monitor) {
            while (queue.size() == limit) {
                monitor.wait();
            }
            queue.offer(value);
            monitor.notifyAll();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (monitor) {
            while (queue.isEmpty()) {
                monitor.wait();
                }
            T val = queue.poll();
            monitor.notifyAll();
            return val;
        }
    }

    public boolean isEmpty() {
        synchronized (monitor) {
            return queue.isEmpty();
        }
    }
}
