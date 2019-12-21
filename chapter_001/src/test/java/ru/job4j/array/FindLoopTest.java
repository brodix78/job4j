package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FindLoopTest {

    @Test
    public void whenArraysHas5Then0() {
        FindLoop find = new FindLoop();
        int input[] = {5, 10, 3};
        int value = 5;
        int rst =find.indexOf(input, value);
        int expect = 0;
        assertThat(rst, is(expect));
    }

    @Test
    public void whenFind3() {
        int input[] = {5, 2, 10, 2, 4};
        int value = 2;
        int start = 2;
        int finish = 4;
        int rst =FindLoop.indexOf(input, value, start, finish);
        int expect = 3;
        assertThat(rst, is(expect));
    }

    @Test
    public void whenSort() {
        int[] input = new int[] {3, 4, 1, 2, 5};
        int[] result = FindLoop.sort(input);
        int[] expect = new int[] {1, 2, 3, 4, 5};
        assertThat(result, is(expect));
    }
}
