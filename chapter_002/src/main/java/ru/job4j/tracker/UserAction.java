package ru.job4j.tracker;

import java.sql.SQLException;
import java.util.function.Consumer;

public interface UserAction {
    String name();
    boolean execute(Input input, Consumer<String> output, ITracker tracker);
}
