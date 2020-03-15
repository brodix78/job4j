package ru.job4j.tracker;

import java.util.function.Consumer;

public class EditItem implements UserAction {
    @Override
    public String name() {
        return "Replace an item";
    }

    @Override
    public boolean execute(Input input, Consumer<String> output, ITracker tracker) {
        String editId;
        editId = input.askString("Input id for edit: ");
        Item itemCh = new Item(input.askString("Input new name: "));
        if (tracker.replaceById(editId, itemCh)) {
            output.accept("Done");
        } else {
            output.accept("No such item");
        }
        return true;
    }
}
