package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DynamicListTest {
    private DynamicList<Integer> list;

    @Before
    public void beforeTest() {
        list = new DynamicList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAddThreeElementsThenUseGetOneResultTwoAndFourIsException() {
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        list.get(4);
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }
}
