package ru.job4j.poly;

public interface Transport {
    void move();
    int passengers();
    double fuelPrice(int fuel);
}