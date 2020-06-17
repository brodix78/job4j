package ru.job4j.syncro;

import org.apache.logging.log4j.core.util.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserStorageTest {

    UserStorage storage = new UserStorage();
    User first = new User(1, 250);
    User second = new User(2, 200);

    @Before
    public void fillStorage() {
        storage.addUser(first);
        storage.addUser(second);
    }

    @Test
    public void makeTwoTransfers() throws InterruptedException {
        Thread one = new Thread(() -> storage.transfer(first, second, 50));
        Thread two = new Thread(() -> storage.transfer(first, second, 100));
        Thread three = new Thread(() -> storage.transfer(second, first, 100));
        one.start();
        two.start();
        three.start();
        one.join();
        two.join();
        three.join();
        assertThat(first.getAmount(), is(200));
        assertThat(second.getAmount(), is(250));
    }
}