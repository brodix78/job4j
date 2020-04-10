package ru.job4j.srp;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.function.Function;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReportEngineTest {

    MemStore store = new MemStore();
    Calendar time = new GregorianCalendar(2020, Calendar.APRIL , 20);
    Employee worker = new Employee("Ivan", time, time, 100);

    @Before
    public void init() {
        store.add(worker);
    }

    @Test
    public void whenOldGenerated() {
        ReportEngine engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenForHRGenerated() {
        ReportEngine engine = new ReportEngine(store, new HrReport());
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append("; ")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenForAccGenerated() {
        Function <Double, Double> func = sal -> sal/50;
        ReportEngine engine = new ReportEngine(store, new AccountantReport(func, "Salary, USD"));
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary, USD;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() / 50).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenForProgrammersGenerated() {
        ReportEngine engine = new ReportEngine(store, new PrgReport());
        String expect = null;
        try {
            expect = new String(Files.readAllBytes(Paths.get("./diffrent/example.html")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenXMLGenerated() {
        ReportEngine engine = new ReportEngine(store, new XMLReport());
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary, USD;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() / 50).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}
