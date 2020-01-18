package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

public class ValidateInputTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<String>() {
        private final PrintStream newout = new PrintStream(out);

        @Override
        public void accept(String s) {
            newout.println(s);
        }

        @Override
        public String toString() {
            return new String(out.toByteArray());
        }
    };


    @Test
    public void whenOk() {
        String[] answers = {"2"};
        assertThat(new ValidateInput(new StubInput(answers), this.output).askInt("", 4), is(2));
    }

    @Test
    public void whenMoreThanMax() {
        String[] answers = {"5", "2"};
        new ValidateInput(new StubInput(answers), this.output).askInt("ss", 4);
        assertThat(this.out.toString(),
                is(String.format("Please select key from menu: 0 - 4" + System.lineSeparator()))
        );
    }

    @Test
    public void whenInvalidData() {
        String[] answers = {"anything", "2"};
        new ValidateInput(new StubInput(answers), this.output).askInt("ss", 4);
        assertThat(this.out.toString(),
                is(String.format("Please enter validate data: 0 - 4" + System.lineSeparator()))
        );
    }
}
