package ru.job4j.syncro;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CountTest {

    private class CountThread extends Thread {
        private final Count count;

        public CountThread(Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenTwoThreadsCountIsTwo() throws InterruptedException {
        Count count = new Count();
        Thread one = new CountThread(count);
        Thread two = new CountThread(count);
        one.start();
        two.start();
        one.join();
        two.join();
        assertThat(count.get(), is(2));
    }
}