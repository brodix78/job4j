package ru.job4j.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NotifyAccountTest {
    @Test
    public void sentWhenSame() {
        List<Account> dataIn = Arrays.asList(new Account("123 12", "Ivan Petrov", "AS123456"),
                new Account("123 12", "Petrov Ivan", "QW123456"));
        Set<Account> expect = new HashSet<>(Arrays.asList(new Account("123 12", "Petrov Ivan", "QW123456")));
        assertThat(new NotifyAccount().sent(dataIn), is(expect));
    }

    @Test
    public void sentWhenDiffrent() {
        List<Account> dataIn = Arrays.asList(new Account("123 12", "Ivan Petrov", "AS123456"),
                new Account("231 12", "Ivan Petrov", "QW123456"));
        Set<Account> expect = new HashSet<>(Arrays.asList(new Account("123 12", "Petrov Ivan", "QW123456"),
                new Account("231 12", "Ivan Petrov", "QW123456")));
        assertThat(new NotifyAccount().sent(dataIn), is(expect));
    }
}