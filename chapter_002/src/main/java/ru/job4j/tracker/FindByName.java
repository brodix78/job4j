package ru.job4j.tracker;

import java.util.List;

public class FindByName implements UserAction {
    @Override
    public String name() {
        return "Find items by name";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        List<Item> itemFN = tracker.findByName(input.askString("Input name: "));
        if (itemFN.size() > 0) {
            for (Item it : itemFN) {
                System.out.println(it.getName() + " id:" + it.getId());
            }
        } else {
            System.out.println("No item");
        }
        return true;
    }
}
