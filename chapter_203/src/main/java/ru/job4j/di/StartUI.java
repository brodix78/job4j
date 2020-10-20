package ru.job4j.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class StartUI {

    @Autowired
    private Store store;

    @Autowired
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
