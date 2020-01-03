package ru.job4j.tracker;

public class DeleteItem implements UserAction {
    @Override
    public String name() {
        return "Delete an item";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        if (tracker.deleteById(input.askString("Input id for delete: "))) {
            System.out.println("Done");
        } else {
            System.out.println("No such item");
        }
        return true;
    }
}
