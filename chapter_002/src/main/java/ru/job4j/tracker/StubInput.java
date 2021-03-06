package ru.job4j.tracker;

import java.util.function.Consumer;

public class StubInput implements Input {
    private String[] answers;
    private int position = 0;
    public Consumer<String> output;

    public StubInput(String[] answers) {
        this.answers = answers;
    }

    @Override
    public String askString(String question) {
        return answers[position++];
    }

    @Override
    public int askInt(String question) {
        return Integer.valueOf(askString(question));
    }

    @Override
    public int askInt(String question, int max) {
        int select = askInt(question);
        if (select >= 0 && select <= max) {
            return select;
        } else {
            throw new IllegalStateException();
        }
    }
}