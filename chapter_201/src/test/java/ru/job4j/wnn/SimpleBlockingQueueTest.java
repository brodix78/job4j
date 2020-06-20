package ru.job4j.wnn;

import org.junit.Test;

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
}