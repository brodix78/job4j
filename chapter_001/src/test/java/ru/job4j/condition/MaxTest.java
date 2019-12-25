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

    @Test
    public void whenMax1_2_3then3() {
        int result = Max.max(1, 2, 3);
        assertThat(result, is(3));
    }

    @Test
    public void whenMax5_100_45then100() {
        int result = Max.max(5, 100, 45);
        assertThat(result, is(100));
    }

    @Test
    public  void whenMax100_100_100then100() {
        int result = Max.max(100, 100, 100);
        assertThat(result, is(100));
    }

    @Test
    public void whenMax1_2_3_4then3() {
        int result = Max.max(1, 2, 3, 4);
        assertThat(result, is(4));
    }

    @Test
    public void whenMax5_23_100_45then100() {
        int result = Max.max(5, 23, 100, 45);
        assertThat(result, is(100));
    }

    @Test
    public  void whenMax100_100_100_100then100() {
        int result = Max.max(100, 100, 100, 100);
        assertThat(result, is(100));
    }
}
