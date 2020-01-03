package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class StartUITest {

    @Test
    public void initTest() {
        StubInput input = new StubInput(new String[] {"0"});
        StubAction action = new StubAction();
        new StartUI().init(new Tracker(), input, new UserAction[] {action});
        assertThat(action.isCall(), is(true));
    }

    @Test
    public void showMenuTest() {
        StubInput input = new StubInput(new String[] {"0"});
        StubAction action = new StubAction();
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new StartUI().init(new Tracker(), input, new UserAction[] {action});
        assertThat(new String(out.toByteArray()),
                is(new StringBuilder()
                        .append("0. Stub action" + System.lineSeparator())
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .toString()));
        System.setOut(stdout);
    }
}