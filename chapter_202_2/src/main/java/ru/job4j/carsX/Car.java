package ru.job4j.carsX;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Car {

    private int id;
    private Engine engine;
    private Set<Driver> drivers = new HashSet<>();

    public static Car of(Engine engine) {
        Car car = new Car();
        car.engine = engine;
        return car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
