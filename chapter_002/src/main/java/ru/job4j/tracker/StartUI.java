package ru.job4j.tracker;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class StartUI {

    private final ITracker tracker;
    private Input input;
    private Consumer<String> output;
    private List<UserAction>userActions;

    public StartUI(Input input, Consumer<String> output, ITracker tracker, List<UserAction> userActions) {
        this.tracker = tracker;
        this.input = input;
        this.output = output;
        this.userActions = userActions;
    }

    public void init() {
        boolean run = true;
        while (run) {
            this.showMenu(userActions, output);
            int select;
            select = input.askInt("Your choice: ", userActions.size() - 1);
            run = userActions.get(select).execute(input, output, tracker);
            output.accept(String.format("%n"));
        }
    }

    private void showMenu(List<UserAction> userActions, Consumer<String> output) {
        for (int i = 0; i < userActions.size(); i++) {
            output.accept(String.format("%s. %s", i, userActions.get(i).name()));
        }

    }

    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        Consumer<String> output = System.out::println;
        Input input = new ValidateInput(new ConsoleInput(output), output);
        List<UserAction> userActions = Arrays.asList(new CreateItem(), new ListItems(), new EditItem(), new DeleteItem(), new FindById(), new FindByName(), new Exit());
        new StartUI(input, output, tracker, userActions).init();
    }
}