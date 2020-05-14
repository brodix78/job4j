package ru.job4j.lsp;

import org.junit.Test;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ActionsTest {

    Parking park = mock(Parking.class);
    Car car = new Car("X444XX");
    UserInterface ui = mock(UserInterface.class);

    @Test
    public void addCarByAction () {
        when(ui.input("Input car number: ")).thenReturn("X444XX");
        when(ui.input("Passenger car?", List.of("yes", "no"))).thenReturn("yes");
        when(park.addCar(car)).thenReturn("1");
        Action action = new AddCar(park);
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            assertThat(arg0, is("Parked at place(s): 1"));
            return null;
        }).when(ui).output(any(String.class));
        action.execute(ui);
    }

/*
    @Test
    public void getCarByAction () {
        BufferedReader input = new BufferedReader(new StringReader ("X444XX%n"));
        ui.setIn = input;
        park.add(car);
        String exp = String.format("Input car number: %nOK%n");
        assertThat(getCar.execute(ui), is (true));
        output.flush();
        assertThat(out.toString(), is (exp));
    }

 */
}