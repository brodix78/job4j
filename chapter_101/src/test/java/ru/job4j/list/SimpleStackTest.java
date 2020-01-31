package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {

    @Test(expected = NoSuchElementException.class)
    public void testPushPollAndException() {
        SimpleStack<Integer> list = new SimpleStack<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        assertThat(list.poll(), is(4));
        assertThat(list.poll(), is(3));
        assertThat(list.poll(), is(2));
        assertThat(list.poll(), is(1));
        list.poll();
    }
}