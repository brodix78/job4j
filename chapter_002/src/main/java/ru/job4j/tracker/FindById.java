package ru.job4j.tracker;

import java.util.function.Consumer;

public class FindById implements UserAction {
    @Override
    public String name() {
        return "Find an item by id";
    }

    @Override
    public boolean execute(Input input, Consumer<String> output, Tracker tracker) {

        Item itemFI = tracker.findById(input.askString("Input id: "));
        if (itemFI != null) {
            output.accept(itemFI.getName() + " id:" + itemFI.getId());
        } else {
            output.accept("No item");
        }
        return true;
    }
}
