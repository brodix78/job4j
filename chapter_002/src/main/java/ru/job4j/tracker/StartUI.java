package ru.job4j.tracker;

import java.util.Scanner;

public class StartUI {

    public void init(Scanner scanner, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            System.out.println();
            int select;
            do{
                select = Integer.valueOf(scanner.nextLine());
            } while (select < 0 || select > 6);
            switch (select) {
                case 0: {
                    System.out.println("Input new item name: ");
                    Item item =new Item(scanner.nextLine());
                    tracker.add(item);
                    break;
                }
                case 1: {
                    for (Item item: tracker.findAll()) {
                        System.out.println(item.getName());
                    }
                    break;
                }
                case 2: {// Этот метод в tracker не реализовывали, если надо допишу
                    break;
                }
                case 3: {// Этот метод в tracker не реализовывали, если надо допишу
                    break;
                }
                case 4: {
                    System.out.println("Input id: ");
                    tracker.findById(scanner.nextLine());
                    break;
                }
                case 5: {
                    System.out.println("Input name: ");
                    for (Item item : tracker.findByName(scanner.nextLine())){
                        System.out.println(item.getName() + " id:" + item.getId());
                    }
                    break;
                }
                case 6: {
                    System.out.println("CUB good :)");
                    run = false;
                    break;
                }
            }
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
