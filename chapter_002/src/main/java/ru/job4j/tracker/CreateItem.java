package ru.job4j.tracker;

public class CreateItem implements UserAction {
    @Override
    public String name() {
        return "Create a new item";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        Item item = new Item(input.askString("Input new item name: "));
        tracker.add(item);
        return true;
    }
}