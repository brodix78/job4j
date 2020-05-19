package ru.job4j.xZero;

import java.util.HashMap;

public interface InOut {
    void showField(Field field);
    boolean makeMove(Field field, String name, String symbol);

    String input(String query);
    void output(String text);
}
