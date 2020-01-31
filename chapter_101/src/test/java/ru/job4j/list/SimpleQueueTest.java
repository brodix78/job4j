package ru.job4j.list;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {
    @Test(expected = NoSuchElementException.class)
    public void testPushPollAndException() {
        SimpleQueue<Integer> list = new SimpleQueue<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        assertThat(list.poll(), is(1));
        assertThat(list.poll(), is(2));
        assertThat(list.poll(), is(3));
        assertThat(list.poll(), is(4));
        list.push(1);
        list.push(2);
        assertThat(list.poll(), is(1));
        assertThat(list.poll(), is(2));
        list.poll();
    }
}
