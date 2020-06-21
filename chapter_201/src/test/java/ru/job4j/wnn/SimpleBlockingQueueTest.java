package ru.job4j.wnn;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleBlockingQueueTest {

    @Test
    public void pollIsWaitingWhenQueueEmpty() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(1);
        Thread one = new Thread(() -> {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        one.start();
        Thread.sleep(100);
        assertThat(one.getState(), is(Thread.State.WAITING));
        one.interrupt();
    }

    @Test
    public void offerIsWaitingWhenQueueIsFull() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(1);
        Thread one = new Thread(() -> {
            try {
                queue.offer(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        one.start();
        Thread.sleep(100);
        Thread two = new Thread(() -> {
            try {
                queue.offer(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        two.start();
        Thread.sleep(100);
        assertThat(two.getState(), is(Thread.State.WAITING));
        one.interrupt();
    }

    @Test
    public void offerIsWaitingWhenQueueIsFullWhileNotPolled() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(1);
        Thread one = new Thread(() -> {
            try {
                queue.offer(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        one.start();
        Thread.sleep(100);
        Thread two = new Thread(() -> {
            try {
                queue.offer(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        two.start();
        Thread.sleep(100);
        assertThat(two.getState(), is(Thread.State.WAITING));
        Thread three = new Thread(() -> {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        three.start();
        Thread.sleep(100);
        assertThat(two.getState(), is(Thread.State.TERMINATED));
    }

    @Test
    public void pollIsWaitingWhenQueueIsEmptyWhileNotOffered() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(1);
        Thread one = new Thread(() -> {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        one.start();
        Thread.sleep(100);
        assertThat(one.getState(), is(Thread.State.WAITING));
        Thread two = new Thread(() -> {
            try {
                queue.offer(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        two.start();
        Thread.sleep(100);
        assertThat(one.getState(), is(Thread.State.TERMINATED));
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        Thread producer = new Thread(() -> IntStream.range(0, 5).forEach(
                value -> {
                    try {
                        queue.offer(value);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
        )
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}