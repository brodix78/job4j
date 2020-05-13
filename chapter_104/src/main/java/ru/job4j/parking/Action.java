package ru.job4j.parking;

public interface Action {
    boolean execute(UserInterface ui);
    String actionName();
}
