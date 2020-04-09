package ru.job4j.srp;

import java.util.List;
import java.util.function.Function;

public class AccountantReport implements Generator {

    private Function<Double, Double> func;
    private String salaryName;

    public AccountantReport(Function<Double, Double> func, String salaryName) {
        this.func = func;
        this.salaryName = salaryName;
    }

    @Override
    public String report(List<Employee> employee) {
        StringBuilder text = new StringBuilder();
        text.append(String.format("Name; Hired; Fired; %s;", salaryName))
                .append(System.lineSeparator());
        for (Employee emp : employee) {
            text.append(emp.getName()).append(";")
                    .append(emp.getHired()).append(";")
                    .append(emp.getFired()).append(";")
                    .append(func.apply(emp.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
