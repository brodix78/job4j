package ru.job4j.tracker;

public class ValidateInput extends ConsoleInput {
    @Override
    public int askInt(String question) {
        boolean valid = false;
        int value = -1;
        do {
            try {
                value = super.askInt(question);
                valid = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data");
            }
        } while (valid == false);
        return value;
    }

    @Override
    public int askInt(String question, int max) {
        boolean valid = false;
        int value = -1;
        do {
            try {
                value = super.askInt(question, max);
                valid = true;
            } catch (IllegalStateException moe) {
                System.out.println("Please select key from menu: 0 - " + max);
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data: 0 - " + max);
            }
        } while (valid == false);
        return value;
    }
}
