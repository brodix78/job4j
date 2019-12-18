package ru.job4j.loop;

public class Fitness {

    public int calc(int ivan, int nik) {
        int months = 0;
        while (ivan < nik) {
            months++;
            ivan *= 3;
            nik *= 2;
        }
    return months;
    }
}
