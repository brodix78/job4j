package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.plaf.SplitPaneUI;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    @Test
    public void addItemTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("Безобразие");
        tracker.add(item);
        assertThat(tracker.getItemByIndex(0).getName(), is("Безобразие"));
    }

    @Test
    public void findAllTest() {
        Tracker tracker = new Tracker();
        String[] names = {"one", "two", "three"};
        Item[] itemCh = new Item[3];
        for (int i = 0; i < names.length; i++) {
            itemCh[i] = new Item(names[i]);
            tracker.add(itemCh[i]);
            itemCh[i].setId(tracker.getItemByIndex(i).getId());
        }
        List<Item> items = tracker.findAll();
        assertThat(items.size(), is(3));
        assertThat(items.get(0), is(itemCh[0]));
        assertThat(items.get(1), is(itemCh[1]));
        assertThat(items.get(2), is(itemCh[2]));
    }

    @Test
    public void findByNameTest() {
        Tracker tracker = new Tracker();
        String[] names = {"one", "two", "three", "two"};
        Item[] itemCh = new Item[4];
        for (int i = 0; i < names.length; i++) {
            itemCh[i] = new Item(names[i]);
            tracker.add(itemCh[i]);
            itemCh[i].setId(tracker.getItemByIndex(i).getId());
        }
        List<Item> items = tracker.findByName("two");
        assertThat(items.size(), is(2));
        assertThat(items.get(0), is(itemCh[1]));
        assertThat(items.get(1), is(itemCh[3]));
    }

    @Test
    public void findByIdTest() {
        Tracker tracker = new Tracker();
        String[] names = {"one", "two", "five"};
        Item[] itemCh = new Item[3];
        for (int i = 0; i < names.length; i++) {
            itemCh[i] = new Item(names[i]);
            tracker.add(itemCh[i]);
            itemCh[i].setId(tracker.getItemByIndex(i).getId());
        }
        Item item = tracker.findById(itemCh[2].getId());
        assertThat(item.getName(), is("five"));
    }
}