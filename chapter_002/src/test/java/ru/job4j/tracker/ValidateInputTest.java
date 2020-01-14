package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ValidateInputTest {
    private PrintStream stdout = System.out;
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOut() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void returnOut() {
        System.setOut(stdout);
    }

    @Test
    public void whenOk() {
        String[] answers = {"2"};
        assertThat(new ValidateInput(new StubInput(answers)).askInt("", 4), is(2));
    }

    @Test
    public void whenMoreThanMax() {
        String[] answers = {"5", "2"};
        System.out.println(new ValidateInput(new StubInput(answers)).askInt("ss", 4));
        assertThat(this.out.toString(),
                is(String.format("Please select key from menu: 0 - 4" + System.lineSeparator() + "2" + System.lineSeparator()))
        );
    }

    @Test
    public void whenInvalidData() {
        String[] answers = {"anything", "2"};
        System.out.println(new ValidateInput(new StubInput(answers)).askInt("ss", 4));
        assertThat(this.out.toString(),
                is(String.format("Please enter validate data: 0 - 4" + System.lineSeparator() + "2" + System.lineSeparator()))
        );
    }
}
