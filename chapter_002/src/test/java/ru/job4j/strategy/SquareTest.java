package ru.job4j.strategy;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SquareTest {
    @Test
    public void squareTesting() {
        Square square = new Square();
        assertThat(square.draw(), is(new StringBuilder()
                .append("+ + + + + +" + System.lineSeparator())
                .append("+         +" + System.lineSeparator())
                .append("+         +" + System.lineSeparator())
                .append("+         +" + System.lineSeparator())
                .append("+         +" + System.lineSeparator())
                .append("+ + + + + +" + System.lineSeparator())
                .toString()));
    }
}