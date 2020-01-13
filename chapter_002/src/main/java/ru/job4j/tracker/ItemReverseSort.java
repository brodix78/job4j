package ru.job4j.tracker;

import java.util.Comparator;

public class ItemReverseSort implements Comparator<Item> {

    @Override
    public int compare(Item second, Item first) {
        return first.getName().compareTo(second.getName());
    }
}
