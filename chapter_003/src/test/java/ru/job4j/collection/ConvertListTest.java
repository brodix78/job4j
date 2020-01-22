package ru.job4j.collection;

import org.junit.Test;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ConvertListTest {
    @Test
    public void when2List() {
        ConvertList convList =  new ConvertList();
        List<int[]> list = Arrays.asList(new int[] {1, 2}, new int[] {3, 4, 5});
        assertThat(convList.toList(list), is(List.of(1, 2, 3, 4, 5)));
    }
}
