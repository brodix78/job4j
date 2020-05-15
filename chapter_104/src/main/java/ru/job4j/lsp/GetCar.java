package ru.job4j.lsp;

import java.util.List;

public class GetCar implements Action{

    Parking park;

    public GetCar(Parking park) {
        this.park = park;
    }

    @Override
    public boolean execute(UserInterface ui) {
        Car car;
        String number = ui.input("Input car number: ");
        car = park.getCar(number);
        if (car != null) {
            ui.output(String.format("Car out"));
        } else {
            ui.output("No such car");
        }
        return car != null;
    }

    @Override
    public String actionName() {
        return "Get a car";
    }
}
