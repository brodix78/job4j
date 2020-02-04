package ru.job4j.set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {

    private SimpleSet<Integer> list;
    Iterator<Integer> it;

    @Before
    public void beforeTest() {
        list = new SimpleSet<>();
        list.add(1);
        list.add(2);
        list.add(null);
        it = list.iterator();
    }

    @Test
    public void iteratorTest() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        Assert.assertNull(it.next());
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenAddSameNoChangeException() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        Assert.assertNull(it.next());
        list.add(2);
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddNewChangeException() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        list.add(3);
        it.hasNext();
    }

    @Test
    public void whenAddNullNoChangeException() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        list.add(null);
        assertThat(it.hasNext(), is(true));
    }
}