package ru.job4j.tracker;

import java.util.Scanner;

public class ConsoleInput implements Input {
    @Override
    public String askString(String question) {
        System.out.print(question);
        return new Scanner(System.in).nextLine();
    }

    @Override
    public int askInt(String question) {
        System.out.print(question);
        return Integer.valueOf(new Scanner(System.in).nextLine());
    }
}
