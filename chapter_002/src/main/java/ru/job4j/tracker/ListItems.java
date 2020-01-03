package ru.job4j.tracker;

public class ListItems implements UserAction {
    @Override
    public String name() {
        return "List of items";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int pos = 0;
        for (Item it: tracker.findAll()) {
            System.out.println("#" + pos++ + ". " + it.getName() + " id:" + it.getId());
        }
        return true;
    }
}
