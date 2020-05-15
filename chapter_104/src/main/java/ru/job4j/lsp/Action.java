package ru.job4j.lsp;

public interface Action {
    boolean execute(UserInterface ui);
    String actionName();
}
