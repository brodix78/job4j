package ru.job4j.tracker;

import java.util.*;

public class Tracker implements ITracker{

    private List<Item> items = new ArrayList<>();

    public Item getItemByIndex(int index) {
        return this.items.get(index);
    }

    public int getPosition() {
        return this.items.size();
    }

    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    public List<Item> findAll() {
        return  this.items;
    }

    public List<Item> findByName(String key) {
        List<Item> itemsByName = new ArrayList<>();
        int size = 0;
        for (Item itemByN : this.items) {
            String name = itemByN.getName();
            if (name.equals(key)) {
                itemsByName.add(itemByN);
            }
        }
        return itemsByName;
    }

    public Item findById(String id) {
        Item item = null;
        int index = indexOf(id);
        if (index != -1) {
            item = this.items.get(index);
        }
        return item;
    }

    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    private int indexOf(String id) {
        int index = -1;
        int i = 0;
        for (Item item : this.items) {
            String theId = item.getId();
            if (id.equals(theId)) {
                index = i;
                break;
            }
            i++;
        }
        return index;
    }

    public boolean deleteById(String id) {
        boolean rsl = false;
        int index = indexOf(id);
        if (index != -1) {
            this.items.remove(index);
            rsl = true;
        }
        return rsl;
    }

    public boolean replaceById(String id, Item item) {
        boolean rsl = false;
        int index = indexOf(id);
        if (index != -1) {
            item.setId(this.items.get(index).getId());
            this.items.set(index, item);
            rsl = true;
        }
        return rsl;
    }
}
