package ru.job4j.sort;

import java.util.Arrays;

public class Machine {
    private final int[] COINS = {10, 5, 2, 1};

    public int[] change(int money, int price) {
        int[] rsl = new int[100];
        int size = 0;
        int coin = 0;
        money = money - price;
        while (money > 0) {
            money = money >= COINS[coin] ? money - (rsl[size++] = COINS[coin]) :  (0 * coin++) + money;
        }
        return Arrays.copyOf(rsl, size);
    }
}