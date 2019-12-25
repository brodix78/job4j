package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TriangleTest {

    @Test
    public void whenExist() {
        Point a = new Point(0, 0);
        Point b = new Point(4, 0);
        Point c = new Point(2, 3);
        Triangle newOne = new Triangle(a, b, c);
        boolean result = newOne.exist();
        assertThat(result, is(true));
    }

    @Test
    public void whenNotExist() {
        Point a = new Point(0, 0);
        Point b = new Point(5, 0);
        Point c = new Point(10, 0);
        Triangle newOne = new Triangle(a, b, c);
        boolean result = newOne.exist();
        assertThat(result, is(false));
    }
}
