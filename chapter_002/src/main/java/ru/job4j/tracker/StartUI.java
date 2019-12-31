package ru.job4j.tracker;

import java.util.Scanner;

public class StartUI {

    public void init(Scanner scanner, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            System.out.println();
            int select;
            do {
                select = Integer.valueOf(scanner.nextLine());
            } while (select < 0 || select > 6);
            switch (select) {
                case 0:
                    System.out.println("Input new item name: ");
                    Item item = new Item(scanner.nextLine());
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
                    System.out.print("Input id for edit: ");
                    editId = scanner.nextLine();
                    System.out.print("Input new name: ");
                    Item itemCh = new Item(scanner.nextLine());
                    tracker.replaceById(editId, itemCh);
                    break;
                case 3:
                    int delPos;
                    System.out.print("Input id for delete: ");
                    tracker.deleteById(scanner.nextLine());
                    break;
                case 4:
                    System.out.println("Input id: ");
                    tracker.findById(scanner.nextLine());
                    break;
                case 5:
                    System.out.println("Input name: ");
                    for (Item it : tracker.findByName(scanner.nextLine())) {
                        System.out.println(it.getName() + " id:" + it.getId());
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
        Scanner scanner = new Scanner(System.in);
        Tracker tracker = new Tracker();
        new StartUI().init(scanner, tracker);
    }
}
