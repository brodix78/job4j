package ru.job4j.tracker;

public class EditItem implements UserAction {
    @Override
    public String name() {
        return "Replace an item";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String editId;
        editId = input.askString("Input id for edit: ");
        Item itemCh = new Item(input.askString("Input new name: "));
        if (tracker.replaceById(editId, itemCh)) {
            System.out.println("Done");
        } else {
            System.out.println("No such item");
        }
        return true;
    }
}
