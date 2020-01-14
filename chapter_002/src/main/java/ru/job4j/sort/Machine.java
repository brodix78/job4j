package ru.job4j.sort;

import java.util.Arrays;

public class Machine {
    private final int[] coins = {10, 5, 2, 1};

    public int[] change(int money, int price) {
        int[] rsl = new int[100];
        int size = 0;
        int coin = 0;
        money = money - price;
        while (money > 0) {
            if (money >= coins[coin]) {
                rsl[size++] = coins[coin];
                money -= coins[coin];
            } else {
                coin++;
            }
        }
        return Arrays.copyOf(rsl, size);
    }
}