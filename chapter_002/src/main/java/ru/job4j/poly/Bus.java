package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void move() {
    }

    @Override
    public int passengers() {
        return 50;
    }

    @Override
    public double fuelPrice(int fuel) {
        double fuelPrice = 1.85 * fuel;
        return fuelPrice;
    }
}
