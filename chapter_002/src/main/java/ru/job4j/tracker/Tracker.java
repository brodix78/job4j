package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

public class Tracker {

    private final Item[] items = new Item[100];
    private int position = 0;

    public Item getItemByIndex(int index) {
        return this.items[index];
    }

    public int getPosition() {
        return this.position;
    }

    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    public Item[] findAll() {
        return  Arrays.copyOf(this.items, this.position);
    }

    public Item[] findByName(String key) {
        Item[] itemsByName = new Item[this.position];
        int size = 0;
        boolean find = false;
        for (int i = 0; i < this.position; i++) {
            String name = this.items[i].getName();
            if (name.equals(key)) {
                itemsByName[size] = this.items[i];
                find = true;
                size++;
            }
        }
        itemsByName = find ? Arrays.copyOf(itemsByName, size) : null;
        return itemsByName;
    }

    public Item findById(String id) {
        Item item = null;
        int index = indexOf(id);
        if (index != -1) {
            item = this.items[index];
        }
        return item;
    }

    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    private int indexOf(String id) {
        int index = -1;
        for (int i = 0; i < this.position; i++) {
            String theId = this.items[i].getId();
            if (id.equals(theId)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void deleteById(String id) {
        int index = indexOf(id);
        if (index != -1) {
            System.arraycopy(this.items,  index + 1, this.items, index, position - index - 1);
            this.position--;
        }
    }

    public void replaceById(String id, Item item) {
        int index = indexOf(id);
        if (index != -1) {
            item.setId(this.items[index].getId());
            items[index] = item;
        }
    }
}
