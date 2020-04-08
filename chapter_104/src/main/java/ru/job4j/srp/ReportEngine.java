package ru.job4j.srp;

import java.util.function.Predicate;

public class ReportEngine {
    private Store store;
    private Generator generator;

    public ReportEngine(Store store, Generator generator) {
        this.store = store;
        this.generator = generator;
    }

    public String generate(Predicate<Employee> filter) {
        return generator.report(store.findBy(filter));
    }
}