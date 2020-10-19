package ru.job4j.di;

public class StartUI {

    private Store store;
    private ConsoleInput console;

    public StartUI(Store store, ConsoleInput console) {
        this.store = store;
        this.console = console;
    }

    public void add(String value) {
        store.add(value);
    }

    public void print() {
        for (String value : store.getAll()) {
            System.out.println(value);
        }
    }
}
