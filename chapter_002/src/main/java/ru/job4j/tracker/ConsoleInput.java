package ru.job4j.tracker;

import java.util.Scanner;
import java.util.function.Consumer;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);
    public Consumer<String> output;

    public ConsoleInput(Consumer<String> output) {
        this.output = output;
    }

    @Override
    public String askString(String question) {
        output.accept(question);
        return this.scanner.nextLine();
    }

    @Override
    public int askInt(String question) {
        output.accept(question);
        return Integer.valueOf(scanner.nextLine());
    }

    @Override
    public int askInt(String question, int max) {
        int select = askInt(question);
        if (!inLimits(select, max)) {
        throw new IllegalStateException(String.format("Out of about %s > [0, %s]", select, max));
        }
        return select;
    }

    public boolean inLimits(int select, int max) {
        return select >= 0 && select <= max;
    }
}