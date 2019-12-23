package ru.job4j.loop;

public class Counter {
    public static int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i < finish + 1; i++) {
            sum += (i % 2 == 0) ? i : 0;
        }
        return sum;
    }
}
