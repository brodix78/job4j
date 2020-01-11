package ru.job4j.tracker;

import java.util.Arrays;
import java.util.List;

public class StartUI {
    public void init(Tracker tracker, Input input, List<UserAction> userActions) {
        boolean run = true;
        while (run) {
            this.showMenu(userActions);
            System.out.println();
            int select;
            select = input.askInt("Your choice: ", userActions.size() - 1);
            run = userActions.get(select).execute(input, tracker);
            System.out.println();
        }
    }

    private void showMenu(List<UserAction> userActions) {
        for (int i = 0; i < userActions.size(); i++) {
            System.out.println(i + ". " + userActions.get(i).name());
        }
    }

    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        Input input = new ValidateInput(new ConsoleInput());
        List<UserAction> userActions = Arrays.asList(new CreateItem(), new ListItems(), new EditItem(), new DeleteItem(), new FindById(), new FindByName(), new Exit());
        new StartUI().init(tracker, input, userActions);
    }
}