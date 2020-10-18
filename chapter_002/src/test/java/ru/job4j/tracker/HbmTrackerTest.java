package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class HbmTrackerTest {

    @Test
    public void addNewItemWhenIdNotNull() {
        HbmTracker store = new HbmTracker();
        Item item = new Item("test");
        store.add(item);
        assertNotNull(item.getId());
    }

    @Test
    public void addNewItemWhenFindAllEqualsIt() {
        HbmTracker store = new HbmTracker();
        Item item = new Item("test");
        store.add(item);
        ArrayList<Item> items = new ArrayList<>(store.findAll());
        assertThat(items.size(), is(1));
        assertThat(items.get(0), is(item));
    }

    @Test
    public void addNewItemAndReplaceItWithNewByIdOnlyNewInStore() {
        HbmTracker store = new HbmTracker();
        Item item = new Item("test");
        store.add(item);
        Item another = new Item("new test");
        store.replaceById(item.getId(), another);
        ArrayList<Item> items = new ArrayList<>(store.findAll());
        assertThat(items.size(), is(1));
        assertThat(items.get(0), is(another));
    }

    @Test
    public void addNewTwoNewItemsWhenDeleteOneByIdOnlySecondStays() {
        HbmTracker store = new HbmTracker();
        Item one = new Item("test1");
        Item two = new Item("test2");
        store.add(one);
        store.add(two);
        ArrayList<Item> items = new ArrayList<>(store.findAll());
        assertThat(items.size(), is(2));
        store.deleteById(one.getId());
        items = new ArrayList<>(store.findAll());
        assertThat(items.size(), is(1));
        assertThat(items.get(0), is(two));
    }

    @Test
    public void addNewTwoNewItemsWhenFindByIsOK() {
        HbmTracker store = new HbmTracker();
        Item one = new Item("test1");
        Item two = new Item("test2");
        store.add(one);
        store.add(two);
        Item rsl = store.findById(one.getId());
        assertThat(rsl, is(one));
    }

    @Test
    public void addNewThreeNewItemsTwowithSameNameWhenFindByNameGettingThemTwo() {
        HbmTracker store = new HbmTracker();
        Item one = new Item("test1");
        Item two = new Item("test1");
        Item three = new Item("test2");
        store.add(one);
        store.add(two);
        store.add(three);
        ArrayList<Item> items1 = new ArrayList<>(store.findAll());
        items1.stream().forEach(item -> System.out.println(item.toString()));
        ArrayList<Item> items = new ArrayList<>(store.findByName("test1"));
        assertThat(items.size(), is(2));
        assert(items.containsAll(List.of(one, two)));
    }
}
