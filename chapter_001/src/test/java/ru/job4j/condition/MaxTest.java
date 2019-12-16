package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {

    @Test
    public void whenMax1to2then2() {
        int result = Max.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenMax5to100then100() {
        int result = Max.max(5, 100);
        assertThat(result, is(100));
    }

    @Test
    public  void whenMax100to100then100() {
        int result = Max.max(100, 100);
        assertThat(result, is(100));
    }
}
