package ru.job4j.loop;

public class CheckPrimeNumber {

    public static boolean check(int finish) {
        int lastcheck = finish / 2;
        for (int i = 2; i <= lastcheck; i++) {
            if ((finish % i) == 0) {
                return false;
            }
        }
        return true;
    }
}
