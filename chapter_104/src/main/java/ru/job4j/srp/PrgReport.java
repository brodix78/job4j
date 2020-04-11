package ru.job4j.srp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

public class PrgReport implements Generator {

    @Override
    public String report(List<Employee> employee) {
        StringBuilder report = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        report.append(row(List.of("Name", "Hired", "Fired", "Salary")));
        for (Employee emp : employee) {
            report.append(row(List.of(emp.getName(),
                    dateFormat.format(emp.getHired().getTime()),
                    dateFormat.format(emp.getFired().getTime()),
                    Double.toString(emp.getSalary()))));
        }
        return table().replace(String.format("CONTENT%n"), report.toString());
    }

    private String row(List<String> cells) {
        StringBuilder row = new StringBuilder();
        row.append(String.format("    <tr>%n"));
        for (String cell : cells) {
            row.append(String.format("        <td>%s</td>%n", cell));
        }
        row.append(String.format("    </tr>%n"));
        return row.toString();
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
