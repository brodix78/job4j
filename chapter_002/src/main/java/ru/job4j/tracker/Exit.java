package ru.job4j.tracker;

import java.util.function.Consumer;

public class Exit implements UserAction {
    @Override
    public String name() {
        return "Exit";
    }

    @Override
    public boolean execute(Input input, Consumer<String> output, ITracker tracker) {
        return false;
    }
}
