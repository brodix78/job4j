package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.iterator.IteratorTwoD;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DynamicListIteratorTest {
    private DynamicListIterator<Integer> it;
    DynamicList<Integer> list;

    @Before
    public void setUp() {
        list = new DynamicList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        it = new DynamicListIterator<>(list);
    }

    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
    }

    @Test(expected = NoSuchElementException.class)
    public void hasNextNextSequentialInvocationAndException() {
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

    @Test(expected = ConcurrentModificationException.class)
    public void thatThrowsExceptionIdListChanged() {
        list.add(5);
        it.next();
    }
}
