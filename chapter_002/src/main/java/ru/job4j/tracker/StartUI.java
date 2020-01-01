package ru.job4j.tracker;

import java.util.Scanner;

public class StartUI {

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
                    Item item = new Item(input.askString("Input new item name: "));
                    tracker.add(item);
                    break;
                case 1:
                    int pos = 0;
                    for (Item it: tracker.findAll()) {
                        System.out.println("#" + pos++ + ". " + it.getName() + " id:" + it.getId());
                    }
                    break;
                case 2:
                    String editId;
                    editId = input.askString("Input id for edit: ");
                    Item itemCh = new Item(input.askString("Input new name: "));
                    tracker.replaceById(editId, itemCh);
                    break;
                case 3:
                    int delPos;
                    tracker.deleteById(input.askString("Input id for delete: "));
                    break;
                case 4:
                    Item itemFI = tracker.findById(input.askString("Input id: "));
                    if (itemFI != null) {
                        System.out.println(itemFI.getName() + " id:" + itemFI.getId());
                    } else {
                        System.out.println("No item");
                    }
                    break;
                case 5:
                    Item[] itemFN = tracker.findByName(input.askString("Input name: "));
                    if (itemFN != null) {
                        for (Item it : itemFN) {
                            System.out.println(it.getName() + " id:" + it.getId());
                        }
                    } else {
                        System.out.println("No item");
                    }
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
