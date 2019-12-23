package ru.job4j.loop;

public class Mortgage {
    public int year(int amount, int salary, double percent) {
        int year = 0;
        do {
            year++;
            int percentForYear = (int) percent * amount / 100;
            amount = amount + percentForYear - salary;
        }
        while (amount > 0);
        return (year);
    }
}
