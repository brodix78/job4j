package ru.job4j.parking;

import java.util.Objects;

public class Car {
    private String number;
    private int places;

    public Car(String number) {
        this.number = number;
        this.places = 1;
    }

    public Car(String number, int places) {
        this.number = number;
        this.places = places;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return number.equals(car.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
