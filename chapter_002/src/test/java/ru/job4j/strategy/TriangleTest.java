package ru.job4j.strategy;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TriangleTest {
    @Test
    public void trianglTest() {
        Triangle tri = new Triangle();
        assertThat(tri.draw(), is(new StringBuilder()
                .append("     +" + System.lineSeparator())
                .append("    + +" + System.lineSeparator())
                .append("   +   +" + System.lineSeparator())
                .append("  +     +" + System.lineSeparator())
                .append(" +       +" + System.lineSeparator())
                .append("+ + + + + +" + System.lineSeparator())
                .toString()));
    }
}