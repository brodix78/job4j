package ru.job4j.exam;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalizeTest {

    @Test
    public void twoAddedTwoDeletedTwoChanged() {
        Analize analize = new Analize();
        List<Analize.User> previous = List.of(new Analize.User(10, "Alex"),
                new Analize.User(11, "Alex"),
                new Analize.User(12, "Alex"),
                new Analize.User(13, "Alex"));
        List<Analize.User> current = List.of(new Analize.User(10, "Max"),
                new Analize.User(11, "Alexandra"),
                new Analize.User(14, "Alex"),
                new Analize.User(15, "Alex"));
        Analize.Info info = analize.diff(previous, current);
        assertThat(info.added, is(2));
        assertThat(info.changed, is(2));
        assertThat(info.deleted, is(2));
    }

    @Test
    public void twoAddedTwoDeleted() {
        Analize analize = new Analize();
        List<Analize.User> previous = List.of(new Analize.User(10, "Alex"),
                new Analize.User(11, "Alex"),
                new Analize.User(12, "Alex"),
                new Analize.User(13, "Alex"));
        List<Analize.User> current = List.of(new Analize.User(10, "Alex"),
                new Analize.User(11, "Alex"),
                new Analize.User(14, "Alex"),
                new Analize.User(15, "Alex"));
        Analize.Info info = analize.diff(previous, current);
        assertThat(info.added, is(2));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(2));
    }

    @Test
    public void threeAddedAllDeleted() {
        Analize analize = new Analize();
        List<Analize.User> previous = List.of(new Analize.User(10, "Alex"),
                new Analize.User(11, "Alex"),
                new Analize.User(12, "Alex"),
                new Analize.User(13, "Alex"));
        List<Analize.User> current = List.of(new Analize.User(18, "Max"),
                new Analize.User(16, "Alexandra"),
                new Analize.User(14, "Alex"));
        Analize.Info info = analize.diff(previous, current);
        assertThat(info.added, is(3));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(4));
    }

    @Test
    public void fourAddedOneChanged() {
        Analize analize = new Analize();
        List<Analize.User> previous = List.of(new Analize.User(10, "Alex"),
                new Analize.User(11, "Alex"),
                new Analize.User(12, "Alex"),
                new Analize.User(13, "Alex"));
        List<Analize.User> current = List.of(new Analize.User(10, "Max"),
                new Analize.User(16, "Alexandra"),
                new Analize.User(17, "Alexandra"),
                new Analize.User(18, "Alexandra"),
                new Analize.User(11, "Alex"),
                new Analize.User(12, "Alex"),
                new Analize.User(13, "Alex"),
                new Analize.User(19, "Alex"));
        Analize.Info info = analize.diff(previous, current);
        assertThat(info.added, is(4));
        assertThat(info.changed, is(1));
        assertThat(info.deleted, is(0));
    }
}