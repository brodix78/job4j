package ru.job4j.xZero;

import java.util.HashMap;

public interface InOut {
    void showField(Field field);
    boolean makeMove(Field field, Player player);

    String input(String query);
    void output(String text);
}
