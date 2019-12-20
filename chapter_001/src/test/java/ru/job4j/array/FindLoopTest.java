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
}
