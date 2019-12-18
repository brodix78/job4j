package ru.job4j.loop;

public class Factorial {
    public static int calc(int n) {
        int result = (n > 1)? n: 1;
        while (n > 1) {
            n = n - 1;
            result *= n;
        }
        return result;
    }
}
