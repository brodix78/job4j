package ru.job4j.lsp;

import org.junit.Test;
import ru.job4j.isp.Action;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserInterfaceTest {

    StringWriter out = new StringWriter();
    PrintWriter output = new PrintWriter(out);
/*
    @Test
    public void testMenu() {
        BufferedReader input = new BufferedReader(new StringReader("3%n"));
        UserInterface ui = new UserInterface(List.of(new AddCar(new SimpleParking()),
                new GetCar(new SimpleParking())));
        ui.setOut = out;
        ui.setIn = input;
        String exp = String.format("1. Park a car;%n2. Leave parking;%n3. Exit.%n%nInput your choice (1-3): ");
        output.flush();
        assertThat(out.toString(), is(exp));
    }

*/

}
