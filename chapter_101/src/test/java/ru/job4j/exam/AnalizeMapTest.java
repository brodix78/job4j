package ru.job4j.exam;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalizeMapTest {

    @Test
    public void twoAddedTwoDeletedTwoChanged() {
        AnalizeMap analizeMap = new AnalizeMap();
        List<AnalizeMap.User> previous = List.of(new AnalizeMap.User(10, "Alex"),
                new AnalizeMap.User(11, "Alex"),
                new AnalizeMap.User(12, "Alex"),
                new AnalizeMap.User(13, "Alex"));
        List<AnalizeMap.User> current = List.of(new AnalizeMap.User(10, "Max"),
                new AnalizeMap.User(11, "Alexandra"),
                new AnalizeMap.User(14, "Alex"),
                new AnalizeMap.User(15, "Alex"));
        AnalizeMap.Info info = analizeMap.diff(previous, current);
        assertThat(info.added, is(2));
        assertThat(info.changed, is(2));
        assertThat(info.deleted, is(2));
    }

    @Test
    public void twoAddedTwoDeleted() {
        AnalizeMap analizeMap = new AnalizeMap();
        List<AnalizeMap.User> previous = List.of(new AnalizeMap.User(10, "Alex"),
                new AnalizeMap.User(11, "Alex"),
                new AnalizeMap.User(12, "Alex"),
                new AnalizeMap.User(13, "Alex"));
        List<AnalizeMap.User> current = List.of(new AnalizeMap.User(10, "Alex"),
                new AnalizeMap.User(11, "Alex"),
                new AnalizeMap.User(14, "Alex"),
                new AnalizeMap.User(15, "Alex"));
        AnalizeMap.Info info = analizeMap.diff(previous, current);
        assertThat(info.added, is(2));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(2));
    }

    @Test
    public void threeAddedAllDeleted() {
        AnalizeMap analizeMap = new AnalizeMap();
        List<AnalizeMap.User> previous = List.of(new AnalizeMap.User(10, "Alex"),
                new AnalizeMap.User(11, "Alex"),
                new AnalizeMap.User(12, "Alex"),
                new AnalizeMap.User(13, "Alex"));
        List<AnalizeMap.User> current = List.of(new AnalizeMap.User(18, "Max"),
                new AnalizeMap.User(16, "Alexandra"),
                new AnalizeMap.User(14, "Alex"));
        AnalizeMap.Info info = analizeMap.diff(previous, current);
        assertThat(info.added, is(3));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(4));
    }

    @Test
    public void fourAddedOneChanged() {
        AnalizeMap analizeMap = new AnalizeMap();
        List<AnalizeMap.User> previous = List.of(new AnalizeMap.User(10, "Alex"),
                new AnalizeMap.User(11, "Alex"),
                new AnalizeMap.User(12, "Alex"),
                new AnalizeMap.User(13, "Alex"));
        List<AnalizeMap.User> current = List.of(new AnalizeMap.User(10, "Max"),
                new AnalizeMap.User(16, "Alexandra"),
                new AnalizeMap.User(17, "Alexandra"),
                new AnalizeMap.User(18, "Alexandra"),
                new AnalizeMap.User(11, "Alex"),
                new AnalizeMap.User(12, "Alex"),
                new AnalizeMap.User(13, "Alex"),
                new AnalizeMap.User(19, "Alex"));
        AnalizeMap.Info info = analizeMap.diff(previous, current);
        assertThat(info.added, is(4));
        assertThat(info.changed, is(1));
        assertThat(info.deleted, is(0));
    }
}