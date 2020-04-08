package ru.job4j.srp;

import java.util.List;

public class StReport implements Generator {

    @Override
    public String report(List<Employee> employee) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary");
        for (Employee emp : employee) {
            text.append(emp.getName()).append(";")
                    .append(emp.getHired()).append(";")
                    .append(emp.getFired()).append(";")
                    .append(emp.getSalary()).append(";");
        }
        return text.toString();
    }
}
