package ru.job4j.generic;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserStoreTest {
    @Test
    public void createAddAndFindTest() {
        UserStore test = new UserStore(3);
        test.add(new User("One"));
        test.add(new User("Two"));
        test.add(new User("Three"));
        assertThat(test.findById("One").getId(), is("One"));
        assertThat(test.findById("Two").getId(), is("Two"));
        assertThat(test.findById("Three").getId(), is("Three"));
    }

    @Test
    public void replaceTest() {
        UserStore test = new UserStore(3);
        test.add(new User("One"));
        test.add(new User("Two"));
        test.add(new User("Three"));
        test.replace("Two", new User("Four"));
        assertThat(test.findById("One").getId(), is("One"));
        assertThat(test.findById("Four").getId(), is("Four"));
        assertThat(test.findById("Three").getId(), is("Three"));
        Assert.assertNull(test.findById("Two"));
    }

    @Test
    public void deleteTest() {
        UserStore test = new UserStore(3);
        test.add(new User("One"));
        test.add(new User("Two"));
        test.add(new User("Three"));
        test.delete("Two");
        assertThat(test.findById("One").getId(), is("One"));
        assertThat(test.findById("Three").getId(), is("Three"));
        Assert.assertNull(test.findById("Two"));
    }

    @Test(expected = NoSuchElementException.class)
    public void storageIsFull() {
        UserStore test = new UserStore(3);
        test.add(new User("One"));
        test.add(new User("Two"));
        test.add(new User("Three"));
        test.add(new User("Four"));
    }
}