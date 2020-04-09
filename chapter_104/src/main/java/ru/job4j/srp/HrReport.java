package ru.job4j.srp;

import java.util.List;

public class HrReport implements Generator {

    @Override
    public String report(List<Employee> employee) {
        StringBuilder report = new StringBuilder();
        report.append("Name; Salary;")
                .append(System.lineSeparator());
        employee.stream().sorted((emp1, emp2) -> (int) (emp2.getSalary() - emp1.getSalary())).
                forEach(emp -> report.append(String.format("%s; %s;%n", emp.getName(), emp.getSalary())));
        return report.toString();
    }
}