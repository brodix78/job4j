package ru.job4j.tracker;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortTest {
    @Test
    public void lowToHigh() {
        Item item = new Item("Безобразие");
        Item item1 = new Item("Аброкадабра");
        Item item2 = new Item("Вакханалия");
        List<Item> list = Arrays.asList(item, item1, item2);
        List<Item> list1 = Arrays.asList(item1, item, item2);
        Collections.sort(list, new ItemSort());
        assertThat(list, is(list1));
    }

    @Test
    public void highToLow() {
        Item item = new Item("Безобразие");
        Item item1 = new Item("Аброкадабра");
        Item item2 = new Item("Вакханалия");
        List<Item> list = Arrays.asList(item, item1, item2);
        List<Item> list1 = Arrays.asList(item2, item, item1);
        Collections.sort(list, new ItemReverseSort());
        assertThat(list, is(list1));
    }

}
