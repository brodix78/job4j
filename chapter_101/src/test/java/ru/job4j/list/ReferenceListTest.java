package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReferenceListTest {

    ReferenceList<Integer> list;

    @Before
    public void beforeTest() {
        list = new ReferenceList<>();
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
        assertThat(list.getLength(), is(3));
    }

    @Test
    public void whenAddMoreThenHundredAndBackMoving() {
        for (int i = 0; i < 120; i++) {
            list.add(i);
        }
        assertThat(list.getLength(), is(123));
        assertThat(list.get(122), is(119));
        assertThat(list.get(120), is(117));
    }
}
