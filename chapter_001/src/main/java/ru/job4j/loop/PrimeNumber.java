package ru.job4j.loop;

public class PrimeNumber {
    public int calc(int finish) {
        int count = 0;
        for (int i = 2; i <= finish; i++) {
            count += CheckPrimeNumber.check(i)? 1: 0;
        }
        return count;
    }
}
