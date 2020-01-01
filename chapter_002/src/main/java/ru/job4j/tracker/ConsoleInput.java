package ru.job4j.tracker;

import java.util.Scanner;

public class ConsoleInput implements Input {
    @Override
    public String askString(String question) {
        System.out.print(question);
        return this.cs();
    }

    @Override
    public int askInt(String question) {
        System.out.print(question);
        return Integer.valueOf(this.cs());
    }

    private String cs() {
        return new Scanner(System.in).nextLine();
    }
}