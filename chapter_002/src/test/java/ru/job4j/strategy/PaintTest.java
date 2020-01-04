package ru.job4j.strategy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PaintTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void returnOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void paintTestWhenSquare() {
        new Paint().draw(new Square());
        assertThat(
                new String(this.out.toByteArray()),
                is(new StringBuilder()
                        .append("+ + + + + +" + System.lineSeparator())
                        .append("+         +" + System.lineSeparator())
                        .append("+         +" + System.lineSeparator())
                        .append("+         +" + System.lineSeparator())
                        .append("+         +" + System.lineSeparator())
                        .append("+ + + + + +" + System.lineSeparator())
                        .append(System.lineSeparator())
                        .toString()
                )
        );
    }

    @Test
    public void paintTestWhenTriangle() {
        new Paint().draw(new Triangle());
        assertThat(
                new String(this.out.toByteArray()),
                is(new StringBuilder()
                        .append("     +" + System.lineSeparator())
                        .append("    + +" + System.lineSeparator())
                        .append("   +   +" + System.lineSeparator())
                        .append("  +     +" + System.lineSeparator())
                        .append(" +       +" + System.lineSeparator())
                        .append("+ + + + + +" + System.lineSeparator())
                        .append(System.lineSeparator())
                        .toString()
                )
        );
    }


}
