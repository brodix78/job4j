package ru.job4j.tracker;

import java.util.function.Consumer;

public class ValidateInput extends ConsoleInput {
    private final Input input;
    private Consumer<String> output;

    public ValidateInput(Input input, Consumer<String> output) {
        super(output);
        this.input = input;
        this.output = output;
    }

    @Override
    public int askInt(String question) {
        boolean valid = false;
        int value = -1;
        do {
            try {
                value = this.input.askInt(question);
                valid = true;
            } catch (NumberFormatException nfe) {
                output.accept("Please enter validate data");
            }
        } while (!valid);
        return value;
    }

    @Override
    public int askInt(String question, int max) {
        boolean valid = false;
        int value = -1;
        do {
            try {
                value = this.input.askInt(question, max);
                valid = true;
            } catch (IllegalStateException moe) {
                output.accept("Please select key from menu: 0 - " + max);
            } catch (NumberFormatException nfe) {
                output.accept("Please enter validate data: 0 - " + max);
            }
        } while (!valid);
        return value;
    }
}
