package ru.job4j.lsp;

import org.junit.Test;

import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ParkingTest {

    @Test
    public void whenParkingNotCargo() {
        Parking park = new SimpleParking(List.of("1", "2"), List.of());
        Car car = new Car("X444XX");
        assertThat(park.addCar(car), is("1"));
    }

    @Test
    public void whenParkingCargoAndNoPlace() {
        Parking park = new SimpleParking(List.of("1", "2"), List.of());
        Car car = new Car("X444XX", 3);
        assertNull(park.addCar(car));
    }

    @Test
    public void whenParkingCargoAndHavePlace() {
        Parking park = new SimpleParking(List.of("1", "2"), List.of());
        Car car = new Car("X444XX", 2);
        assertThat(park.addCar(car), is("1-2"));
    }

    @Test
    public void whenParkingCargoAndHavePlaceForCargo() {
        Parking park = new SimpleParking(List.of("1", "2"), List.of("1"));
        Car car = new Car("X444XX", 2);
        assertThat(park.addCar(car), is("Cargo 1"));
    }

    @Test
    public void whenGetCarAndCarOnParking() {
        Parking park = new SimpleParking(List.of("1", "2"), List.of());
        Car car = new Car("X444XX");
        park.addCar(car);
        assertThat(park.getCar("X444XX"), is(car));
    }

    @Test
    public void whenGetCarAndCarNotOnParking() {
        Parking park = new SimpleParking(List.of("1", "2"), List.of());
        Car car = new Car("X444XX");
        park.addCar(car);
        assertNull(park.getCar("X454XX"));
    }
}
