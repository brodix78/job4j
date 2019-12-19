package ru.job4j.loop;

public class Mortgage {
    public int year(int amount, int salary, double percent) {
        int year = 0;
        while (((amount * 100 + amount * percent) / 100) > salary) {
            year++;
            amount = (int) (amount * (100 + percent) / 100 - salary);
        }
        return (year + 1);
    }
}
