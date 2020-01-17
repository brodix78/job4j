package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DepartmentsTest {

    @Test
    public void whenMissed() {
        List<String> dataIn = Arrays.asList("k1/s1");
        List<String> excpect = Arrays.asList("k1", "k1/s1");
        assertThat(new Departments().filGaps(dataIn), is(excpect));
    }

    @Test
    public void whenNoChanges() {
        List<String> dataIn = Arrays.asList("k1", "k1/s1");
        List<String> expect = Arrays.asList("k1", "k1/s1");
        assertThat(new Departments().filGaps(dataIn), is(expect));
    }

    @Test
    public void testAscSort() {
        List<String> dataIn = Arrays.asList("k1/s1/ss3", "k2/s2/ss2");
        List<String> expect = Arrays.asList("k1", "k1/s1", "k1/s1/ss3", "k2", "k2/s2", "k2/s2/ss2");
        Departments dep = new Departments();
        List<String> rsl = dep.filGaps(dataIn);
        dep.ascSort(rsl);
        assertThat(rsl, is(expect));
    }

    @Test
    public void testDescSort() {
        List<String> dataIn = Arrays.asList("k1/s1/ss3", "k2/s2/ss2");
        List<String> excpect = Arrays.asList("k2/s2/ss2", "k2/s2", "k2", "k1/s1/ss3", "k1/s1", "k1");
        Departments dep = new Departments();
        List<String> rsl = dep.filGaps(dataIn);
        dep.descSort(rsl);
        assertThat(rsl, is(excpect));
    }
}
