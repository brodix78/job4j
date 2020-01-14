package ru.job4j.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FullSearchTest {
    @Test
    public void extractNumbersTest() {
        List<Task> tasks = Arrays.asList(
                new Task("1", "One"),
                new Task("2", "Two"),
                new Task("1", "One"));
        assertThat(FullSearch.extractNumbers(tasks), is(new HashSet<>(Arrays.asList("1", "2"))));
    }
}
