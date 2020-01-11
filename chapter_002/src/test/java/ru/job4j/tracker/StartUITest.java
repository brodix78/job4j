package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class StartUITest {
    private PrintStream stdout = System.out;
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOut() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void returnOut() {
        System.setOut(this.stdout);
    }

    @Test
    public void initTest() {
        StubInput input = new StubInput(new String[] {"0"});
        StubAction action = new StubAction();
        new StartUI().init(new Tracker(), input, Arrays.asList(action));
        assertThat(action.isCall(), is(true));
    }

    @Test
    public void showMenuTest() {
        StubInput input = new StubInput(new String[] {"0"});
        StubAction action = new StubAction();
        new StartUI().init(new Tracker(), input, Arrays.asList(action));
        assertThat(new String(this.out.toByteArray()),
                is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add("0. Stub action")
                        .add(System.lineSeparator())
                        .toString()));
        }
}