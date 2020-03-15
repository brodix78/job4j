package ru.job4j.tracker;

import java.sql.SQLException;
import java.util.List;

public interface ITracker {
    Item add(Item item);
    boolean replaceById(String id, Item item);
    boolean deleteById(String id);
    List<Item> findAll();
    List<Item> findByName(String key);
    Item findById(String id);
}
