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
        assertThat(park.add(Car), is("1"));
    }

    @Test
    public void whenParkingCargoAndNoPlace() {
        Parking park = new SimpleParking(List.of("1", "2"), List.of());
        Car car = new Car("X444XX", 3);
        assertThat(park.add(Car), is("No place"));
    }

    @Test
    public void whenParkingCargoAndHavePlace() {
        Parking park = new SimpleParking(List.of("1", "2"), List.of());
        Car car = new Car("X444XX", 2);
        assertThat(park.add(Car), is("1;2"));
    }

    @Test
    public void whenParkingCargoAndHavePlaceForCargo() {
        Parking park = new SimpleParking(List.of("1", "2"), List.of("1"));
        Car car = new Car("X444XX", 2);
        assertThat(park.add(Car), is("Cargo 1"));
    }

    @Test
    public void whenGetCarAndCarOnParking() {
        Parking park = new SimpleParking(List.of("1", "2"), List.of());
        Car car = new Car("X444XX");
        park.add(car);
        assertThat(park.get("X444XX"), is(car));
    }

    @Test
    public void whenGetCarAndCarNotOnParking() {
        Parking park = new SimpleParking(List.of("1", "2"), List.of());
        Car car = new Car("X444XX");
        park.add(Car);
        assertNull(park.get("X454XX"));
    }
}
