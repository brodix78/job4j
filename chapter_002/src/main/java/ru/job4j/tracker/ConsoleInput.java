package ru.job4j.tracker;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String askString(String question) {
        System.out.print(question);
        return this.scanner.nextLine();
    }

    @Override
    public int askInt(String question) {
        System.out.print(question);
        return Integer.valueOf(scanner.nextLine());
    }

    @Override
    public int askInt(String question, int max) {
        int select = askInt(question);
        if (select >= 0 && select <= max) {
            return select;
        } else {
            throw new IllegalStateException(String.format("Out of about %s > [0, %s]", select, max));
        }
    }
}