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
        for (int i = 0; i < this.position; i++) {
            String name = this.items[i].getName();
            if (name.equals(key)) {
                itemsByName[size] = this.items[i];
                size++;
            }
        }
        itemsByName = Arrays.copyOf(itemsByName, size);
        return itemsByName;
    };

    public Item findById(String id) {
        Item itemsById = null;
        for (int i = 0; i < this.position; i++) {
            String theId = this.items[i].getId();
            if (theId.equals(id)) {
                itemsById = this.items[i];
                break;
            }
        }
        return itemsById;
    }

    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    public void delItem(int delPos) {
        for (int i = delPos; i < (position - 1); i++) {
            this.items[i] = this.items[i + 1];
        }
        this.items[this.position - 1] = null;
        this.position--;
    }

    public void editItem(int editPos, String name) {
        this.items[editPos].setName(name);
    }
}
