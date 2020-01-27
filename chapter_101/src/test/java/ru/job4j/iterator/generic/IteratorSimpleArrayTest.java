package ru.job4j.iterator.generic;

import generic.IteratorSimpleArray;
import generic.SimpleArray;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class IteratorSimpleArrayTest {
    private Iterator<Integer> it;

    @Before
    public void setUp() {
        SimpleArray<Integer> test = new SimpleArray<Integer>(4);
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        it = new IteratorSimpleArray<Integer>(test);
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnAllNumbers() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(false));
        it.next();
    }
}
