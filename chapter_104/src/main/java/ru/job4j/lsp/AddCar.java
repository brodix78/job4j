package ru.job4j.lsp;

import java.util.List;

public class AddCar implements Action{

    Parking park;

    public AddCar(Parking park) {
        this.park = park;
    }

    @Override
    public boolean execute(UserInterface ui) {
        String rsl;
        String number = ui.input("Input car number: ");
        int places = 1;
        if (ui.input("Passenger car?", List.of("yes", "no")).equals("no")) {
            places = ui.inputNumb("Number of passengers parking places need: ");
        }
        rsl = park.addCar(new Car(number, places));
        if (rsl != null) {
            ui.output(String.format("Parked at place(s): %s", rsl));
        } else {
            ui.output("No free places");
        }
        return rsl != null;
    }

    @Override
    public String actionName() {
        return "Park a car";
    }
}