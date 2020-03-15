package ru.job4j.tracker;

import java.util.function.Consumer;

public class ListItems implements UserAction {
    @Override
    public String name() {
        return "List of items";
    }

    @Override
    public boolean execute(Input input, Consumer<String> output, ITracker tracker) {
        int pos = 0;
        for (Item it: tracker.findAll()) {
            output.accept("#" + pos++ + ". " + it.getName() + " id:" + it.getId());
        }
        return true;
    }
}
