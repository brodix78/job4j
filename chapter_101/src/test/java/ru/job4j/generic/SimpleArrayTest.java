package ru.job4j.generic;

import ru.job4j.generic.SimpleArray;
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class SimpleArrayTest {
    @Test
    public void createAndAddTest() {
        SimpleArray<Integer> test = new SimpleArray<Integer>(1);
        test.add(5);
        assertThat(test.size(), is(1));
        assertThat(test.get(0), is(5));
    }

    @Test
    public void setAndRemoveTest() {
        SimpleArray<Integer> test = new SimpleArray<Integer>(10);
        test.add(1);
        test.add(2);
        test.add(3);
        test.set(1, 4);
        assertThat(test.get(1), is(4));
        test.remove(1);
        assertThat(test.get(0), is(1));
        assertThat(test.get(1), is(3));
        Assert.assertNull(test.get(2));
    }
}
