package ru.job4j.srp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PrgReport implements Generator {

    @Override
    public String report(List<Employee> employee) {
        StringBuilder report = new StringBuilder();
        report.append(String.format("      <tr>%n"));
        report.append(String.format("        <td>Name</td>%n"));
        report.append(String.format("        <td>Hired</td>%n"));
        report.append(String.format("        <td>Fired</td>%n"));
        report.append(String.format("        <td>Salary</td>%n"));
        report.append(String.format("      </tr>%n"));
        for (Employee emp : employee) {
            report.append(String.format("      <tr>%n"));
            report.append(String.format("        <td>%s</td>%n", emp.getName()));
            report.append(String.format("        <td>%s</td>%n", emp.getHired()));
            report.append(String.format("        <td>%s</td>%n", emp.getFired()));
            report.append(String.format("        <td>%s</td>%n", emp.getSalary()));
            report.append(String.format("      </tr>%n"));
        }
        return table().replace("CONTENT",report.toString());
    }

    private String table() {
        String table = null;
        try {
            table = new String(Files.readAllBytes(Paths.get("./diffrent/table.pat")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return table;
    }
}
