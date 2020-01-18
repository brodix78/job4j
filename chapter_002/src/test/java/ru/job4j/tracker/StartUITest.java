package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class StartUITest {

  private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    private final Consumer<String> output = new Consumer<String>() {
        private final PrintStream newout = new PrintStream(out);

        @Override
        public void accept(String s) {
            newout.println(s);
        }

        @Override
        public String toString() {
            return new String((out.toByteArray()));
        }
    };

    @Test
    public void initTest() {
        StubInput input = new StubInput(new String[] {"0"});
        StubAction action = new StubAction();
        new StartUI().init(new Tracker(), input, output, Arrays.asList(action));
        assertThat(action.isCall(), is(true));
    }

    @Test
    public void showMenuTest() {
        StubInput input = new StubInput(new String[] {"0"});
        StubAction action = new StubAction();
        new StartUI().init(new Tracker(), input, output, Arrays.asList(action));
        assertThat(this.out.toString(),
                is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add("0. Stub action")
                        .add(System.lineSeparator())
                        .toString()));
        }
}