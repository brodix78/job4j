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
        /*
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";");
        }

         */
        return generator.report(store.findBy(filter));
    }
}
