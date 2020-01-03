package ru.job4j.tracker;

public class FindById implements UserAction {
    @Override
    public String name() {
        return "Find an item by id";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        Item itemFI = tracker.findById(input.askString("Input id: "));
        if (itemFI != null) {
            System.out.println(itemFI.getName() + " id:" + itemFI.getId());
        } else {
            System.out.println("No item");
        }
        return true;
    }
}
