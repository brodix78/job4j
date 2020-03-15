package ru.job4j.tracker;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Consumer;

public class FindByName implements UserAction {
    @Override
    public String name() {
        return "Find items by name";
    }

    @Override
    public boolean execute(Input input, Consumer<String> output, ITracker tracker) {
        List<Item> itemFN = tracker.findByName(input.askString("Input name: "));
        if (itemFN.size() > 0) {
            for (Item it : itemFN) {
                output.accept(it.getName() + " id:" + it.getId());
            }
        } else {
            output.accept("No item");
        }
        return true;
    }
}
