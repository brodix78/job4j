package ru.job4j.strategy;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PaintTest {
    @Test
    public void paintTest() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square());
        assertThat(
                new String(out.toByteArray()),
                is(new StringBuilder()
                        .append("+ + + + + +\n")
                        .append("+         +\n")
                        .append("+         +\n")
                        .append("+         +\n")
                        .append("+         +\n")
                        .append("+ + + + + +\n")
                        .toString()
                )
        );
        System.setOut(stdout);
    }

}
