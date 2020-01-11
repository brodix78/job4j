package ru.job4j.list;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertMatrix2ListTest {
    @Test
    public void convertTest() {
        ConvertMatrix2List convert = new ConvertMatrix2List();
        int[][] test = {{1, 2}, {3, 4}};
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        assertThat(convert.toList(test), is(list));
    }
}
