package ru.job4j.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.list.DynamicList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OwnHashMapTest {
    //Iterator<String> it = map.iterator();

    @Test
    public void addGet() {
        OwnHashMap<String, String> map = new OwnHashMap<>();
        map.insert("One", "A");
        map.insert("Two", "B");
        map.insert("Three", "C");
        map.insert("Four", "D");
        assertThat(map.get("One"), is("A"));
        assertThat(map.get("Two"), is("B"));
        assertThat(map.get("Three"), is("C"));
        assertThat(map.get("Four"), is("D"));
    }

    @Test
    public void addSameKeyReplaceValue() {
        OwnHashMap<String, String> map = new OwnHashMap<>();
        map.insert("One", "A");
        map.insert("One", "B");
        assertThat(map.get("One"), is("B"));
    }

    @Test
    public void deleteItemAfterGetNull() {
        OwnHashMap<String, String> map = new OwnHashMap<>();
        map.insert("One", "A");
        map.insert("Two", "B");
        map.insert("Three", "C");
        map.insert("Four", "D");
        map.delete("Two");
        Assert.assertNull(map.get("Two"));
    }


    @Test(expected = NoSuchElementException.class)
    public void iterator() {
        OwnHashMap<String, String> map = new OwnHashMap<>();
        map.insert("One", "A");
        map.insert("Two", "B");
        map.insert("Three", "C");
        map.insert("Four", "D");
        Iterator<String> it = map.iterator();
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        assertThat(it.hasNext(), is(false));
        it.next();
    }

}
