package ru.job4j.tracker;

public class StartUI {

    public static void createItem(Input input, Tracker tracker) {
        Item item = new Item(input.askString("Input new item name: "));
        tracker.add(item);
    }

    public static void listItems(Tracker tracker) {
        int pos = 0;
        for (Item it: tracker.findAll()) {
            System.out.println("#" + pos++ + ". " + it.getName() + " id:" + it.getId());
        }
    }

    public static void replace(Input input, Tracker tracker) {
        String editId;
        editId = input.askString("Input id for edit: ");
        Item itemCh = new Item(input.askString("Input new name: "));
        if (tracker.replaceById(editId, itemCh)) {
            System.out.println("Done");
        } else {
            System.out.println("No such item");
        }
    }

    public static void delete(Input input, Tracker tracker) {
        if (tracker.deleteById(input.askString("Input id for delete: "))) {
            System.out.println("Done");
        } else {
            System.out.println("No such item");
        }
    }

    public static void findById(Input input, Tracker tracker) {
        Item itemFI = tracker.findById(input.askString("Input id: "));
        if (itemFI != null) {
            System.out.println(itemFI.getName() + " id:" + itemFI.getId());
        } else {
            System.out.println("No item");
        }
    }

    public static void findByName(Input input, Tracker tracker) {
        Item[] itemFN = tracker.findByName(input.askString("Input name: "));
        if (itemFN.length > 0) {
            for (Item it : itemFN) {
                System.out.println(it.getName() + " id:" + it.getId());
            }
        } else {
            System.out.println("No item");
        }
    }

    public void init(Tracker tracker, Input input) {
        boolean run = true;
        while (run) {
            this.showMenu();
            System.out.println();
            int select;
            do {
                select = input.askInt("Your choice: ");
            } while (select < 0 || select > 6);
            switch (select) {
                case 0:
                    this.createItem(input, tracker);
                    break;
                case 1:
                    this.listItems(tracker);
                    break;
                case 2:
                    this.replace(input, tracker);
                    break;
                case 3:
                    this.delete(input, tracker);
                    break;
                case 4:
                    this.findById(input, tracker);
                    break;
                case 5:
                    this.findByName(input, tracker);
                    break;
                case 6:
                    System.out.println("C U B good :)");
                    run = false;
                    break;
                default: break;
            }
            System.out.println();
        }
    }

    private void showMenu() {
        String[] menu = {"Add new item",
                "Show all items",
                "Edit item",
                "Delete item",
                "Find item by id",
                "Find items by name",
                "Exit Program"};
        for (int i = 0; i < menu.length; i++) {
            System.out.println(i + ". " + menu[i]);
        }
    }

    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        Input input = new ConsoleInput();
        new StartUI().init(tracker, input);
    }
}
