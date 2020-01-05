package ru.job4j.tracker;

public class StartUI {
    public void init(Tracker tracker, Input input, UserAction[] userActions) {
        boolean run = true;
        while (run) {
            this.showMenu(userActions);
            System.out.println();
            int select;
            select = input.askInt("Your choice: ", userActions.length - 1);
            run = userActions[select].execute(input, tracker);
            System.out.println();
        }
    }

    private void showMenu(UserAction[] userActions) {
        for (int i = 0; i < userActions.length; i++) {
            System.out.println(i + ". " + userActions[i].name());
        }
    }

    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        Input input = new ValidateInput();
        UserAction[] userActions = {new CreateItem(), new ListItems(), new EditItem(), new DeleteItem(), new FindById(), new FindByName(), new Exit()};
        new StartUI().init(tracker, input, userActions);
    }
}