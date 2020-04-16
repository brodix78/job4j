package ru.job4j.lsp;

import org.junit.Test;

import java.util.LinkedHashMap;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ParkingTest {


    @Test
    public void whenParkingNotCargo() {
        Parking park = new Parking();
        LinkedHashMap<String, Car> pk = new LinkedHashMap<>();
        pk.put("1", null);
        pk.put("2", null);
        park.setParking(pk);
        Car car = new Car("X444XX");
        assertThat(park.add(Car), is("1"));
    }

    @Test
    public void whenParkingCargoAndNoPlace() {
        Parking park = new Parking();
        LinkedHashMap<String, Car> pk = new LinkedHashMap<>();
        pk.put("1", null);
        pk.put("2", null);
        park.setParking(pk);
        Car car = new Car("X444XX", 3);
        assertThat(park.add(Car), is("no place"));
    }

    @Test
    public void whenParkingCargoAndHavePlace() {
        Parking park = new Parking();
        LinkedHashMap<String, Car> pk = new LinkedHashMap<>();
        pk.put("1", null);
        pk.put("2", null);
        park.setParking(pk);
        Car car = new Car("X444XX", 2);
        assertThat(park.add(Car), is("1;2"));
    }

    @Test
    public void whenParkingCargoAndHavePlaceForCargo() {
        Parking park = new Parking();
        LinkedHashMap<String, Car> pk = new LinkedHashMap<>();
        pk.put("1", null);
        pk.put("2", null);
        park.setParking(pk);
        LinkedHashMap<String, Car> pkC = new LinkedHashMap<>();
        pkC.put("1", null);
        park.setCargoParking(pkC);
        Car car = new Car("X444XX", 2);
        assertThat(park.add(Car), is("Cargo1"));
    }

    @Test
    public void whenGetCarAndCarOnParking() {
        Parking park = new Parking();
        LinkedHashMap<String, Car> pk = new LinkedHashMap<>();
        pk.put("1", null);
        park.setParking(pk);
        Car car = new Car("X444XX");
        park.add(Car);
        assertThat(park.get("X444XX"), is(car));
        assertThat(park.add(Car), is("1"));
    }

    @Test
    public void whenGetCarAndCarNotOnParking() {
        Parking park = new Parking();
        LinkedHashMap<String, Car> pk = new LinkedHashMap<>();
        pk.put("1", null);
        park.setParking(pk);
        Car car = new Car("X444XX");
        park.add(Car);
        assertNull(park.get("X454XX"));
    }
}
